package com.saas.employee.dto.request;

import com.saas.employee.enums.Gender;
import com.saas.employee.enums.RelationshipType;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyRequest {

    @NotNull(message = "Relationship type cannot be null")
    private RelationshipType relationshipType;

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

    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "Date of birth must be a past date")
    private LocalDate dateOfBirth;

    @Size(min = 2, message = "House number must be at least 2 characters")
    private String houseNumber;

    @Pattern(regexp = "^\\+?[0-9 .\\-()]{7,15}$", message = "Home telephone must be a valid phone number")
    private String homeTelephone;

    @Pattern(regexp = "^\\+?[0-9 .\\-()]{7,15}$", message = "Office telephone must be a valid phone number")
    private String officeTelephone;

    @Pattern(regexp = "^\\+?[0-9 .\\-()]{7,15}$", message = "Mobile number must be a valid phone number")
    private String mobileNumber;

    @Email(message = "Email must be a valid email address")
    private String email;

    @Size(min = 2, message = "PO Box must be at least 2 characters")
    private String poBox;

    @NotNull(message = "Emergency contact cannot be null")
    private boolean isEmergencyContact;

    @AssertTrue(message = "Date of birth must be a valid past date")
    private boolean isDateOfBirthValid() {
        return dateOfBirth != null && dateOfBirth.isBefore(LocalDate.now());
    }
}
