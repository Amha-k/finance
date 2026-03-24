package com.saas.employee.mapper;

import com.saas.employee.dto.request.EmployeeHistoryRequest;
import com.saas.employee.dto.response.EmployeeHistoryResponse;
import com.saas.employee.enums.ProcessType;
import com.saas.employee.model.DutyStation;
import com.saas.employee.model.Employee;
import com.saas.employee.model.EmployeeHistory;
import com.saas.employee.repository.EmployeeHistoryRepository;
import com.saas.employee.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class EmployeeHistoryMapper {

    private final EmployeeHistoryRepository employeeHistoryRepository;
    private final ValidationUtil validationUtil;

    public EmployeeHistory mapToEntity(Employee employee,
                                       ProcessType processType,
                                       String createdBy) {

        EmployeeHistory employeeHistory = new EmployeeHistory();
        employeeHistory.setEmployee(employee);
        employeeHistory.setDepartmentId(employee.getDepartmentId());
        employeeHistory.setJobId(employee.getJobId());
        employeeHistory.setPayGradeId(employee.getPayGradeId());
        employeeHistory.setDutyStation(employee.getDutyStation());
        employeeHistory.setTenantId(employee.getTenantId());
        employeeHistory.setProcessType(processType);
        employeeHistory.setStartDate(this.getStartDate(employee));
        employeeHistory.setEndDate(LocalDate.now());
        employeeHistory.setCreatedBy(createdBy);

        return employeeHistory;
    }

    public EmployeeHistoryResponse mapToDto(EmployeeHistory employeeHistory) {

        EmployeeHistoryResponse response = new EmployeeHistoryResponse();
        response.setId(employeeHistory.getId());
        response.setEmployeeId(employeeHistory.getEmployee().getId());
        response.setDepartmentId(employeeHistory.getDepartmentId());
        response.setJobId(employeeHistory.getJobId());
        response.setPayGradeId(employeeHistory.getPayGradeId());
        response.setStartDate(employeeHistory.getStartDate());
        response.setEndDate(employeeHistory.getEndDate());
        response.setDutyStationId(employeeHistory.getDutyStation().getId());
        response.setProcessType(employeeHistory.getProcessType().name());
        response.setTenantId(employeeHistory.getTenantId());
        response.setCreatedAt(employeeHistory.getCreatedAt());
        response.setUpdatedAt(employeeHistory.getUpdatedAt());
        response.setCreatedBy(employeeHistory.getCreatedBy());
        response.setUpdatedBy(employeeHistory.getUpdatedBy());

        return response;
    }

    public Employee mapUpdateRequest(Employee employee,
                                         EmployeeHistoryRequest request) {

        if (request.getDepartmentId() != null) {
            employee.setDepartmentId(request.getDepartmentId());
        }

        if (request.getJobId() != null) {
            employee.setJobId(request.getJobId());
        }

        if (request.getPayGradeId() != null) {
            employee.setPayGradeId(request.getPayGradeId());
        }

        if (request.getDutyStationId() != null) {

            DutyStation dutyStation = validationUtil
                    .getDutyStationById(employee.getTenantId(), request.getDutyStationId());
            employee.setDutyStation(dutyStation);
        }

        return employee;
    }

    private LocalDate getStartDate(Employee employee) {

        List<EmployeeHistory> employeeHistories = employeeHistoryRepository
                .findByTenantIdAndEmployeeId(employee.getTenantId(), employee.getId());
        if (employeeHistories.isEmpty()) {
             return employee.getHiredDate();
        } else {
            LocalDate now = LocalDate.now();
            return employeeHistories.stream()
                    .map(EmployeeHistory::getEndDate) // Extract endDate
                    .filter(Objects::nonNull) // Exclude null endDates
                    .min(Comparator.comparingLong(endDate ->
                            Math.abs(ChronoUnit.DAYS.between(endDate, now))))
                    .orElse(now);
        }
    }
}
