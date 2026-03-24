package com.saas.employee.dto.request;

import com.saas.employee.enums.EmploymentType;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceRequest {

    @NotBlank(message = "Institution cannot be blank")
    @Size(min = 2, message = "Institution must be at least 2 characters")
    private String institution;

    @NotNull(message = "Employment type cannot be null")
    private EmploymentType employmentType;

    @NotBlank(message = "Job title cannot be blank")
    @Size(min = 2, message = "Job title must be at least 2 characters")
    private String jobTitle;

    @NotNull(message = "Salary cannot be null")
    @DecimalMin(value = "0.0", message = "Salary must be greater than or equal to 0")
    private Double salary;

    @NotNull(message = "Start date cannot be null")
    @Past(message = "Start date must be a past date")
    private LocalDate startDate;

    @NotNull(message = "End date cannot be null")
    @PastOrPresent(message = "End date must be a past or present date")
    private LocalDate endDate;

    @NotBlank(message = "Responsibility cannot be blank")
    @Size(min = 2, message = "Responsibility must be at least 2 characters")
    private String responsibility;

    @NotBlank(message = "Reason for termination cannot be blank")
    @Size(min = 2, message = "Reason for termination must be at least 2 characters")
    private String reasonForTermination;

    @AssertTrue(message = "End date must be after start date")
    public boolean isEndDateValid() {
        if (startDate == null || endDate == null) {
            return true;
        }
        return endDate.isAfter(startDate);
    }
}
