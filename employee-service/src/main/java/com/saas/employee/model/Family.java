package com.saas.employee.model;

import com.saas.employee.enums.Gender;
import com.saas.employee.enums.RelationshipType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "employee_families")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Family extends Base {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RelationshipType relationshipType;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String middleName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    private String houseNumber;
    private String homeTelephone;
    private String officeTelephone;
    private String mobileNumber;

    @Email
    private String email;

    private String poBox;

    @Column(nullable = false)
    private boolean isEmergencyContact;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false, updatable = false)
    private Employee employee;
}
