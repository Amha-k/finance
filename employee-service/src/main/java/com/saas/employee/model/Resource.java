package com.saas.employee.model;

import com.saas.employee.enums.ResourceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "resources")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resource extends Base {

    @Column(nullable = false)
    private String resourceName;

    @Column(nullable = false)
    private ResourceStatus status;

    private Set<String> requiredRoles = new HashSet<>();

    @Column(nullable = false)
    private String tenantAbbreviatedName;

    private String description;
}
