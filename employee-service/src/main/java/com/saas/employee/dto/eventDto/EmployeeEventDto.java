package com.saas.employee.dto.eventDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEventDto {

    private UUID id;
    private UUID tenantId;
    private String employeeId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
}
