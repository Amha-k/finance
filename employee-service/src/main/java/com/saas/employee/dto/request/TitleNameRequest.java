package com.saas.employee.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TitleNameRequest {

    @NotBlank(message = "Title name cannot be blank")
    @Size(min = 2, message = "Title name must be at least 2 characters")
    private String titleName;

    private String description;
}
