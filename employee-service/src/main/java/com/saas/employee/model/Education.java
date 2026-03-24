package com.saas.employee.model;

import com.saas.employee.enums.EducationType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "employee_educations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education extends Base {

    @Column(nullable = false)
    private UUID educationLevelId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EducationType educationType;

    @Column(nullable = false)
    private UUID fieldOfStudyId;

    @Column(nullable = false)
    private String institution;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    private String award;

    @Column(nullable = false)
    private Double result;

    private String fileName;
    private String fileType;
    @Lob
    @Column(length = 50000000)
    private byte[] fileBytes;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false, updatable = false)
    private Employee employee;
}
