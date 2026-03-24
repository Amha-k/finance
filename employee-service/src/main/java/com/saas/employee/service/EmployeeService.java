package com.saas.employee.service;

import com.saas.employee.dto.clientDto.*;
import com.saas.employee.dto.eventDto.EmployeeEventDto;
import com.saas.employee.dto.request.EmployeeRequest;
import com.saas.employee.dto.response.EmployeeResponse;
import com.saas.employee.enums.EmploymentType;
import com.saas.employee.exception.ResourceExistsException;
import com.saas.employee.exception.ResourceNotFoundException;
import com.saas.employee.mapper.EmployeeMapper;
import com.saas.employee.model.DutyStation;
import com.saas.employee.model.Employee;
import com.saas.employee.repository.EmployeeRepository;
import com.saas.employee.utility.FileUtil;
import com.saas.employee.utility.ValidationUtil;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final ValidationUtil validationUtil;
    private final EmployeeEventService employeeEventService;

    @Transactional
    public EmployeeResponse addEmployee(UUID tenantId,
                                        EmployeeRequest request,
                                        MultipartFile file) throws IOException {

        if (employeeRepository.existsByTenantIdAndEmployeeId(tenantId, request.getEmployeeId())) {
            throw new ResourceExistsException(
                    "Employee with employee Id '" + request.getEmployeeId() + "' already exists");
        }
        if (employeeRepository.existsByTenantIdAndEmail(tenantId, request.getEmail())) {
            throw new ResourceExistsException(
                    "Employee with email '" + request.getEmail() + "' already exists");
        }
        Employee employee = employeeMapper.mapToEntity(tenantId, request, file);
        if (employee.getEmploymentType().equals(EmploymentType.CONTRACT) && employee.getEndDate() == null) {
            throw new IllegalArgumentException(
                    "When the employment type is contract, you must set the end date");
        }
        employee = employeeRepository.save(employee);
        EmployeeEventDto event = employeeMapper.mapToEvent(employee);
        employeeEventService.createEmployeeEvent(event);
        return employeeMapper.mapToDto(employee);
    }

    public List<EmployeeResponse> getAllEmployees(UUID tenantId) {

        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .filter(emp -> emp.getTenantId().equals(tenantId))
                .map(employeeMapper::mapToDto)
                .toList();
    }

    public EmployeeResponse getEmployeeById(UUID tenantId,
                                            UUID employeeId) {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        return employeeMapper.mapToDto(employee);
    }

    public EmployeeResponse getEmployeeByEmployeeId(UUID tenantId, String employeeId) {

        Employee employee = validationUtil.getEmployeeByEmployeeId(tenantId, employeeId);
        return employeeMapper.mapToDto(employee);
    }

    public EmployeeResponse getEmployeeByName(UUID tenantId,
                                              String firstName,
                                              String middleName,
                                              String lastName) {

        Employee employee = employeeRepository.findByTenantIdAndFirstNameAndMiddleNameAndLastName(
                tenantId, firstName, middleName, lastName)
                .filter(emp -> emp.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee not found with name '" +
                                firstName + " " + middleName + " " + lastName + "'"));
        return employeeMapper.mapToDto(employee);
    }

    public List<EmployeeResponse> searchEmployee(UUID tenantId, String keyword) {

        List<Employee> employees = employeeRepository.searchEmployees(tenantId, keyword);
        return employees.stream()
                .map(employeeMapper::mapToDto)
                .toList();
    }

    public List<EmployeeResponse> getEmployeesByDepartmentId(UUID tenantId,
                                                             UUID departmentId) {

        List<Employee> employees = employeeRepository
                .findByTenantIdAndDepartmentId(tenantId, departmentId);
        return employees.stream()
                .map(employeeMapper::mapToDto)
                .toList();
    }

    public List<EmployeeResponse> getEmployeesByShiftId(UUID tenantId,
                                                        UUID shiftId) {

        List<Employee> employees = employeeRepository
                .findByTenantIdAndShiftId(tenantId, shiftId);
        return employees.stream()
                .map(employeeMapper::mapToDto)
                .toList();
    }

    public byte[] getEmployeeProfileImageById(UUID tenantId,
                                              UUID employeeId) {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        byte[] fileBytes = FileUtil.decompressFile(employee.getProfileImageBytes());
        if (fileBytes.length == 0) {
            throw new ResourceNotFoundException("Image data is not available");
        }
        return fileBytes;
    }

    public List<EmployeeResponse> getEmployeesByEmploymentType(UUID tenantId,
                                                               String employmentType) {

        List<Employee> employees = employeeRepository.findByTenantIdAndEmploymentType(
                tenantId, EmploymentType.valueOf(employmentType.toUpperCase()));
        return employees.stream().map(employeeMapper::mapToDto).toList();
    }

    public List<EmployeeResponse> getEmployeesByDutyStation(UUID tenantId,
                                                            UUID stationId) {

        DutyStation dutyStation = validationUtil.getDutyStationById(tenantId, stationId);
        List<Employee> employees = employeeRepository
                .findByTenantIdAndDutyStation(tenantId, dutyStation);
        return employees.stream().map(employeeMapper::mapToDto).toList();
    }

    @Transactional
    public EmployeeResponse updateEmployee(UUID tenantId,
                                           UUID employeeId,
                                           EmployeeRequest request,
                                           MultipartFile file) throws IOException {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        if (employeeRepository.existsByTenantIdAndEmployeeIdAndIdNot(
                tenantId, request.getEmployeeId(), employee.getId())) {
            throw new ResourceExistsException(
                    "Employee with employee Id '" + request.getEmployeeId() + "' already exists");
        }
        employee = employeeMapper.mapUpdateRequest(tenantId, employee, request, file);
        employee = employeeRepository.save(employee);
        EmployeeEventDto event = employeeMapper.mapToEvent(employee);
        employeeEventService.updateEmployeeEvent(event);
        return employeeMapper.mapToDto(employee);
    }

    public EmployeeResponse renewEmployeeContract(UUID tenantId,
                                                  UUID employeeId,
                                                  LocalDate endDate) {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        if (!employee.getEmploymentType().equals(EmploymentType.CONTRACT)) {
            throw new IllegalArgumentException("No need to renew contract for permanent employee");
        }
        employee.setEndDate(endDate);
        employee = employeeRepository.save(employee);
        return employeeMapper.mapToDto(employee);
    }

    public String updateEmployeeEmail(UUID tenantId,
                                      UUID employeeId,
                                      String email) {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        employee.setEmail(email);
        employee = employeeRepository.save(employee);
        return employee.getEmail();
    }

    @Transactional
    public void updateEmployeesShift(UUID tenantId,
                                     UUID shiftId,
                                     List<UUID> employeeIds) {

        ShiftDto shift = validationUtil.getShiftById(shiftId);
        List<Employee> employees = employeeRepository.findAllById(employeeIds);
        for (Employee employee : employees) {
            if (!shiftId.equals(employee.getShiftId())) {
                employee.setShiftId(shift.getId());
            }
        }
        employeeRepository.saveAll(employees);
    }

    @Transactional
    public void deleteEmployee(UUID tenantId,
                               UUID employeeId) {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        EmployeeEventDto event = employeeMapper.mapToEvent(employee);
        employeeEventService.deleteEmployeeEvent(event);
        employeeRepository.delete(employee);
    }
}
