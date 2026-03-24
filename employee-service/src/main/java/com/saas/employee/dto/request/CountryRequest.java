package com.saas.employee.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryRequest {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, message = "Name must be at least 2 characters")
    private String name;

    @NotBlank(message = "Abbreviated name cannot be blank")
    @Size(min = 2, message = "Abbreviated name must at least 2 characters")
    private String abbreviatedName;

    @NotBlank(message = "Code cannot be blank")
    @Size(min = 2, message = "Code must be at least 2 characters")
    private String code;
}
