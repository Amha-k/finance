package com.saas.employee.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferEmployeeRequest {

    @NotNull(message = "Department id cannot be null")
    private UUID departmentId;

    @NotNull(message = "Duty station id cannot be null")
    private UUID dutyStationId;
}
