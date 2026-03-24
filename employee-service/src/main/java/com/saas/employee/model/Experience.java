package com.saas.employee.model;

import com.saas.employee.enums.EmploymentType;
import jakarta.persistence.*;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "employee_experiences")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experience extends Base {

    @Column(nullable = false)
    private String institution;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    @Column(nullable = false)
    private String jobTitle;

    @Column(nullable = false)
    private Double salary;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false)
    private String responsibility;

    @Column(nullable = false)
    private String reasonForTermination;

    private String fileName;
    private String fileType;
    @Lob
    @Column(length = 50000000)
    private byte[] fileBytes;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false, updatable = false)
    private Employee employee;
}
