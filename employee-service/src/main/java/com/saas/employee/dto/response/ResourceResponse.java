package com.saas.employee.dto.response;

import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceResponse extends BaseResponse {

    private UUID id;
    private String resourceName;
    private Set<String> requiredRoles;
    private String status;
    private String description;
}
