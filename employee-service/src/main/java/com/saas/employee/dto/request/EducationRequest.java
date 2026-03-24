package com.saas.employee.dto.request;

import com.saas.employee.enums.EducationType;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationRequest {

    @NotNull(message = "Education level ID cannot be null")
    private UUID educationLevelId;

    @NotNull(message = "Education type cannot be null")
    private EducationType educationType;

    @NotNull(message = "Field of study ID cannot be null")
    private UUID fieldOfStudyId;

    @NotBlank(message = "Institution cannot be blank")
    @Size(min = 2, message = "Institution must be at least 2 characters")
    private String institution;

    @NotNull(message = "Start date cannot be null")
    @Past(message = "Start date must be in the past")
    private LocalDate startDate;

    @NotNull(message = "End date cannot be null")
    @PastOrPresent(message = "End date must be in the past or present")
    private LocalDate endDate;

    private String award;

    @NotNull(message = "Result cannot be null")
    @DecimalMin(value = "0.0", message = "Result cannot be less than 0.0")
    @DecimalMax(value = "100.0", message = "Result cannot be more than 100.0")
    private Double result;

    @AssertTrue(message = "End date must be after start date")
    public boolean isEndDateValid() {
        if (startDate == null || endDate == null) {
            return true;
        }
        return endDate.isAfter(startDate);
    }
}
