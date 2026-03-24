package com.saas.employee.dto.request;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

import com.saas.employee.enums.EmploymentType;
import com.saas.employee.enums.Gender;
import com.saas.employee.enums.MaritalStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

    @NotBlank(message = "Employee ID cannot be blank")
    @Size(min = 2, message = "Employee ID must be at least 2 characters")
    private String employeeId;

    @NotBlank(message = "First name cannot be blank")
    @Size(min = 2, message = "First name must be at least 2 characters")
    private String firstName;

    @NotBlank(message = "Middle name cannot be blank")
    @Size(min = 2, message = "Middle name must be at least 2 characters")
    private String middleName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 2, message = "Last name must be at least 2 characters")
    private String lastName;

    @NotNull(message = "Gender cannot be null")
    private Gender gender;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotNull(message = "Marital status cannot be null")
    private MaritalStatus maritalStatus;

    @NotNull(message = "Employment type cannot be null")
    private EmploymentType employmentType;

    @NotNull(message = "Department ID cannot be null")
    private UUID departmentId;

    @NotNull(message = "Job ID cannot be null")
    private UUID jobId;

    @NotNull(message = "Pay grade ID cannot be null")
    private UUID payGradeId;

    @NotNull(message = "Hired date cannot be null")
    @PastOrPresent(message = "Hired date must be in the past or present")
    private LocalDate hiredDate;

    @FutureOrPresent(message = "End date must be in the future or present")
    private LocalDate endDate;

    @Size(min = 12, max = 19, message = "Fayda number must be minimum 12 and maximum 19 characters")
    private String faydaNumber;

    @Size(min = 2, message = "Passport number must be at least 2 characters")
    private String passportNumber;

    @Size(min = 2, message = "TIN number must be at least 2 characters")
    private String tinNumber;

    @Size(min = 2, message = "Pension number must be at least 2 characters")
    private String pensionNumber;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email must be a valid email address")
    private String email;

    @NotNull(message = "Title name ID cannot be null")
    private UUID titleNameId;

    @NotNull(message = "Duty station ID cannot be null")
    private UUID dutyStationId;

    @NotNull(message = "Country ID cannot be null")
    private UUID countryId;

    @NotNull(message = "Shift ID cannot be null")
    private UUID shiftId;

    @AssertTrue(message = "End date must be after hired date")
    public boolean isEndDateValid() {
        if (hiredDate == null || endDate == null) {
            return true;
        }
        return endDate.isAfter(hiredDate);
    }
}
