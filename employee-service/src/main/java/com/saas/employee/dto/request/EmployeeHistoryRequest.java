package com.saas.employee.dto.request;

import com.saas.employee.enums.ProcessType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeHistoryRequest {

    @NotNull(message = "tenant id cannot be null")
    private UUID tenantId;

    @NotNull(message = "employee id cannot be null")
    private UUID employeeId;

    private UUID departmentId;

    private UUID jobId;

    private UUID payGradeId;

    private UUID dutyStationId;

    @NotNull(message = "Process type cannot be null")
    private ProcessType processType;

    @NotBlank(message = "Created by cannot be blank")
    private String createdBy;
}
