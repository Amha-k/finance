package com.saas.employee.mapper;

import com.saas.employee.dto.clientDto.DepartmentDto;
import com.saas.employee.dto.clientDto.JobDto;
import com.saas.employee.dto.clientDto.PayGradeDto;
import com.saas.employee.dto.clientDto.ShiftDto;
import com.saas.employee.dto.eventDto.EmployeeEventDto;
import com.saas.employee.dto.request.EmployeeRequest;
import com.saas.employee.dto.response.EmployeeResponse;
import com.saas.employee.enums.EmployeeStatus;
import com.saas.employee.model.*;
import com.saas.employee.utility.FileUtil;
import com.saas.employee.utility.ValidationUtil;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {

    private final ValidationUtil validationUtil;

    public Employee mapToEntity(UUID tenantId,
                                EmployeeRequest request,
                                MultipartFile file) throws IOException {

        DepartmentDto department = validationUtil.getDepartmentById(tenantId, request.getDepartmentId());
        JobDto job = validationUtil.getJobById(tenantId, department.getId(), request.getJobId());
        PayGradeDto payGrade = validationUtil.getPayGradeById(tenantId, job.getId(), request.getPayGradeId());
        TitleName titleName = validationUtil.getTitleNameById(tenantId, request.getTitleNameId());
        DutyStation dutyStation = validationUtil.getDutyStationById(tenantId, request.getDutyStationId());
        Country country = validationUtil.getCountryById(tenantId, request.getCountryId());
        ShiftDto shift = validationUtil.getShiftById(request.getShiftId());

        Employee employee = new Employee();
        employee.setTenantId(tenantId);
        employee.setDepartmentId(department.getId());
        employee.setJobId(job.getId());
        employee.setPayGradeId(payGrade.getId());
        employee.setTitleName(titleName);
        employee.setEmployeeId(request.getEmployeeId());
        employee.setFirstName(request.getFirstName());
        employee.setMiddleName(request.getMiddleName());
        employee.setLastName(request.getLastName());
        employee.setGender(request.getGender());
        employee.setDateOfBirth(request.getDateOfBirth());
        employee.setDateOfBirth(request.getDateOfBirth());
        employee.setMaritalStatus(request.getMaritalStatus());
        employee.setEmploymentType(request.getEmploymentType());
        employee.setHiredDate(request.getHiredDate());
        employee.setEndDate(request.getEndDate());
        employee.setDutyStation(dutyStation);
        employee.setCountry(country);
        employee.setFaydaNumber(request.getFaydaNumber());
        employee.setPassportNumber(request.getPassportNumber());
        employee.setTinNumber(request.getTinNumber());
        employee.setPensionNumber(request.getPensionNumber());
        employee.setEmail(request.getEmail());
        employee.setShiftId(shift.getId());
        employee.setEmployeeStatus(EmployeeStatus.ACTIVE);

        return getEmployeeFile(employee, file);
    }

    public EmployeeResponse mapToDto(Employee employee) {

        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setTenantId(employee.getTenantId());
        response.setEmployeeId(employee.getEmployeeId());
        response.setDepartmentId(employee.getDepartmentId());
        response.setJobId(employee.getJobId());
        response.setPayGradeId(employee.getPayGradeId());
        response.setTitleNameId(employee.getTitleName().getId());
        response.setFirstName(employee.getFirstName());
        response.setMiddleName(employee.getMiddleName());
        response.setLastName(employee.getLastName());
        response.setGender(employee.getGender().name());
        response.setDateOfBirth(employee.getDateOfBirth());
        response.setAge(calculateAge(employee.getDateOfBirth()));
        response.setMaritalStatus(employee.getMaritalStatus().name());
        response.setEmploymentType(employee.getEmploymentType().name());
        response.setJobId(employee.getJobId());
        response.setDutyStationId(employee.getDutyStation().getId());
        response.setCountryId(employee.getCountry().getId());
        response.setFaydaNumber(employee.getFaydaNumber());
        response.setPassportNumber(employee.getPassportNumber());
        response.setTinNumber(employee.getTinNumber());
        response.setPensionNumber(employee.getPensionNumber());
        response.setEmail(employee.getEmail());
        response.setHiredDate(employee.getHiredDate());
        response.setEndDate(employee.getEndDate());
        response.setShiftId(employee.getShiftId());
        response.setEmployeeStatus(employee.getEmployeeStatus().name());
        response.setProfileImageName(employee.getProfileImageName());
        response.setProfileImageType(employee.getProfileImageType());
        response.setProfileImageBytes(employee.getProfileImageBytes());
        response.setCreatedAt(employee.getCreatedAt());
        response.setUpdatedAt(employee.getUpdatedAt());
        response.setCreatedBy(employee.getCreatedBy());
        response.setUpdatedBy(employee.getUpdatedBy());

        return response;
    }

    public Employee mapUpdateRequest(UUID tenantId,
                                   Employee employee,
                                   EmployeeRequest request,
                                   MultipartFile file) throws IOException {

        DepartmentDto department = validationUtil.getDepartmentById(tenantId, request.getDepartmentId());
        JobDto job = validationUtil.getJobById(tenantId, department.getId(), request.getJobId());
        PayGradeDto payGrade = validationUtil.getPayGradeById(tenantId, job.getId(), request.getPayGradeId());
        TitleName titleName = validationUtil.getTitleNameById(tenantId, request.getTitleNameId());
        DutyStation dutyStation = validationUtil.getDutyStationById(tenantId, request.getDutyStationId());
        Country country = validationUtil.getCountryById(tenantId, request.getCountryId());
        ShiftDto shift = validationUtil.getShiftById(request.getShiftId());

        if (request.getDepartmentId() != null) {
            employee.setDepartmentId(department.getId());
        }
        if (request.getJobId() != null) {
            employee.setJobId(job.getId());
        }
        if (request.getPayGradeId() != null) {
            employee.setPayGradeId(payGrade.getId());
        }
        if (request.getTitleNameId() != null) {
            employee.setTitleName(titleName);
        }
        if (request.getEmployeeId() != null) {
            employee.setEmployeeId(request.getEmployeeId());
        }
        if (request.getFirstName() != null) {
            employee.setFirstName(request.getFirstName());
        }
        if (request.getMiddleName() != null) {
            employee.setMiddleName(request.getMiddleName());
        }
        if (request.getLastName() != null) {
            employee.setLastName(request.getLastName());
        }
        if (request.getGender() != null) {
            employee.setGender(request.getGender());
        }
        if (request.getDateOfBirth() != null) {
            employee.setDateOfBirth(request.getDateOfBirth());
        }
        if (request.getMaritalStatus() != null) {
            employee.setMaritalStatus(request.getMaritalStatus());
        }
        if (request.getEmploymentType() != null) {
            employee.setEmploymentType(request.getEmploymentType());
        }
        if (request.getHiredDate() != null) {
            employee.setHiredDate(request.getHiredDate());
        }
        if (request.getEndDate() != null) {
            employee.setEndDate(request.getEndDate());
        }
        if (request.getDutyStationId() != null) {
            employee.setDutyStation(dutyStation);
        }
        if (request.getCountryId() != null) {
            employee.setCountry(country);
        }
        if (request.getFaydaNumber() != null) {
            employee.setFaydaNumber(request.getFaydaNumber());
        }
        if (request.getPassportNumber() != null) {
            employee.setPassportNumber(request.getPassportNumber());
        }
        if (request.getTinNumber() != null) {
            employee.setTinNumber(request.getTinNumber());
        }
        if (request.getPensionNumber() != null) {
            employee.setPensionNumber(request.getPensionNumber());
        }
        if (request.getEmail() != null) {
            employee.setEmail(request.getEmail());
        }

        if (request.getShiftId() != null) {
            employee.setShiftId(shift.getId());
        }

        return getEmployeeFile(employee, file);
    }

    public EmployeeEventDto mapToEvent(Employee employee) {

        EmployeeEventDto event = new EmployeeEventDto();
        event.setId(employee.getId());
        event.setTenantId(employee.getTenantId());
        event.setEmployeeId(employee.getEmployeeId());
        event.setFirstName(employee.getFirstName());
        event.setMiddleName(employee.getMiddleName());
        event.setLastName(employee.getLastName());
        event.setEmail(employee.getEmail());

        return event;
    }

    private Employee getEmployeeFile(Employee employee,
                                     MultipartFile file) throws IOException {

        if (file != null && !file.isEmpty()) {
            String contentType = file.getContentType();
            boolean isPdf = MediaType.APPLICATION_PDF_VALUE.equals(contentType);
            boolean isDocx = "application/vnd.openxmlformats-officedocument.wordprocessingml.document".equals(contentType);
            boolean isImage = contentType != null && contentType.startsWith("image/");

            if (!(isPdf || isDocx || isImage)) {
                throw new IllegalArgumentException("Only PDF, DOCX, and image files are allowed!");
            }
            employee.setProfileImageName(file.getOriginalFilename());
            employee.setProfileImageType(file.getContentType());
            employee.setProfileImageBytes(FileUtil.compressFile(file.getBytes()));
        }

        return employee;
    }

    public static int calculateAge(LocalDate dob) {

        LocalDate curDate = LocalDate.now();
        if (dob != null) {
            return Period.between(dob, curDate).getYears();
        } else {
            return 0;
        }
    }
}
