package com.saas.employee.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DutyStationRequest {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, message = "Name must be at least 2 characters")
    private String name;

    private String description;
}
