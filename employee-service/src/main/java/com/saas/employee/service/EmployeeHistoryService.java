package com.saas.employee.service;

import com.saas.employee.dto.request.EmployeeHistoryRequest;
import com.saas.employee.dto.response.EmployeeHistoryResponse;
import com.saas.employee.exception.ResourceNotFoundException;
import com.saas.employee.mapper.EmployeeHistoryMapper;
import com.saas.employee.model.Employee;
import com.saas.employee.model.EmployeeHistory;
import com.saas.employee.repository.EmployeeHistoryRepository;
import com.saas.employee.repository.EmployeeRepository;
import com.saas.employee.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeHistoryService {

    private final EmployeeHistoryRepository employeeHistoryRepository;
    private final EmployeeHistoryMapper employeeHistoryMapper;
    private final EmployeeRepository employeeRepository;
    private final ValidationUtil validationUtil;

    @Transactional
    public void addEmployeeHistory(EmployeeHistoryRequest request) {

        Employee employee = validationUtil
                .getEmployeeById(request.getTenantId(), request.getEmployeeId());
        EmployeeHistory employeeHistory = employeeHistoryMapper
                .mapToEntity(employee, request.getProcessType(), request.getCreatedBy());
        employeeHistoryRepository.save(employeeHistory);
        employee = employeeHistoryMapper.mapUpdateRequest(employee, request);
        employeeRepository.save(employee);
    }

    public List<EmployeeHistoryResponse> getAllEmployeeHistories(UUID tenantId,
                                                                 UUID employeeId) {

        List<EmployeeHistory> employeeHistories = employeeHistoryRepository
                .findByTenantIdAndEmployeeId(tenantId, employeeId);
        return employeeHistories.stream()
                .map(employeeHistoryMapper::mapToDto)
                .toList();
    }

    public EmployeeHistoryResponse getEmployeeHistoryById(UUID tenantId,
                                                          UUID historyId) {

        EmployeeHistory employeeHistory = employeeHistoryRepository.findById(historyId)
                .filter(h -> h.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee history not found with id '" + historyId + "'"
                ));
        return employeeHistoryMapper.mapToDto(employeeHistory);
    }
}
