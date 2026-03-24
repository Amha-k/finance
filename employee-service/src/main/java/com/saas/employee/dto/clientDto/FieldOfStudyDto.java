package com.saas.employee.dto.clientDto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldOfStudyDto {
    private UUID id;
    private String fieldOfStudy;
    private String description;
    private UUID tenantId;

    // Getters and Setters
}
