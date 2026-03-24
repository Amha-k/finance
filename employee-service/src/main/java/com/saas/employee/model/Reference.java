package com.saas.employee.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "employee_references")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reference extends Base {

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String jobTitle;

    @Column(nullable = false)
    private String workAddress;

    @Column(nullable = false)
    private String email;

    private String description;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false, updatable = false)
    private Employee employee;
}
