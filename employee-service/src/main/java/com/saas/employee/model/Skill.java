package com.saas.employee.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "employee_skills")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill extends Base {

    @Column(nullable = false)
    private String skillType;

    @Column(nullable = false)
    private String skillLevel;

    private String description;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false, updatable = false)
    private Employee employee;
}
