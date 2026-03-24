package com.saas.employee.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillRequest {

    @NotBlank(message = "Skill type cannot be blank")
    @Size(min = 2, message = "Skill type must be at least 2 characters")
    private String skillType;

    @NotBlank(message = "Skill level cannot be blank")
    @Size(min = 2, message = "Skill level must be at least 2 characters")
    private String skillLevel;

    private String description;
}
