package com.saas.employee.dto.request;

import com.saas.employee.enums.AddressType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

    @NotNull(message = "Address type cannot be null")
    private AddressType addressType;

    @NotNull(message = "Location ID cannot be null")
    private UUID locationId;

    @Size(min = 2, message = "House number must be at least 2 characters")
    private String houseNumber;

    @Pattern(regexp = "^\\+?[0-9 .\\-()]{7,15}$", message = "Invalid home telephone number")
    private String homeTelephone;

    @Pattern(regexp = "^\\+?[0-9 .\\-()]{7,15}$", message = "Invalid office telephone number")
    private String officeTelephone;

    @Pattern(regexp = "^\\+?[0-9 .\\-()]{7,15}$", message = "Invalid mobile number")
    private String mobileNumber;

    @Email(message = "Invalid email address")
    private String email;

    @Size(min = 2, message = "PO Box must be at least 2 characters")
    private String poBox;
}
