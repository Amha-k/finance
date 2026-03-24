package com.saas.employee.model;

import com.saas.employee.enums.EmployeeStatus;
import com.saas.employee.enums.EmploymentType;
import com.saas.employee.enums.Gender;
import com.saas.employee.enums.MaritalStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends Base {

    @Column(nullable = false, unique = true)
    private String employeeId;

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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    @Column(nullable = false)
    private UUID departmentId;

    @Column(nullable = false)
    private UUID jobId;

    @Column(nullable = false)
    private UUID payGradeId;

    @Column(nullable = false)
    private LocalDate hiredDate;

    private LocalDate endDate;
    private String faydaNumber;
    private String passportNumber;
    private String tinNumber;
    private String pensionNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private UUID shiftId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;

    private String profileImageName;
    private String profileImageType;
    @Lob
    @Column(length = 50000000)
    private byte[] profileImageBytes;

    @ManyToOne
    @JoinColumn(name = "title_name_id", nullable = false)
    private TitleName titleName;

    @ManyToOne
    @JoinColumn(name = "duty_station_id", nullable = false)
    private DutyStation dutyStation;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @OneToMany(mappedBy = "employee")
    private Set<Address> addresses;

    @OneToMany(mappedBy = "employee")
    private Set<Education> educations;

    @OneToMany(mappedBy = "employee")
    private Set<Experience> experiences;

    @OneToMany(mappedBy = "employee")
    private Set<Family> families;

    @OneToMany(mappedBy = "employee")
    private Set<Language> languages;

    @OneToMany(mappedBy = "employee")
    private Set<Reference> references;

    @OneToMany(mappedBy = "employee")
    private Set<Skill> skills;

    @OneToMany(mappedBy = "employee")
    private Set<Training> trainings = new HashSet<>();

    @OneToMany(mappedBy = "employee")
    private Set<EmployeeHistory> employeeHistories = new HashSet<>();
}
