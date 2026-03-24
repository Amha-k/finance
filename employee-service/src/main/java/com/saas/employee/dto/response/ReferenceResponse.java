package com.saas.employee.dto.response;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReferenceResponse extends BaseResponse {
    private String fullName;
    private String phoneNumber;
    private String jobTitle;
    private String workAddress;
    private String email;
    private String description;
    private UUID employeeId;
}
