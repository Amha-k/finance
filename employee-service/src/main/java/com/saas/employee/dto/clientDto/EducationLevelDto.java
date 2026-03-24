package com.saas.employee.dto.clientDto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationLevelDto {
    private UUID id;
    private String educationLevelName;
    private String description;
    private UUID tenantId;
}
