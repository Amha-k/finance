package com.saas.employee.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReferenceRequest {

    @NotBlank(message = "Full name cannot be blank")
    @Size(min = 2, message = "Full name must be at least 2 characters")
    private String fullName;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^\\+?[0-9 .\\-()]{7,15}$", message = "Phone number must be a valid phone number")
    private String phoneNumber;

    @NotBlank(message = "Job title cannot be blank")
    @Size(min = 2, message = "Job title must be at least 2 characters")
    private String jobTitle;

    @NotBlank(message = "Work address cannot be blank")
    @Size(min = 2, message = "Work address must be at least 2 characters")
    private String workAddress;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email must be a valid email address")
    private String email;

    private String description;
}
