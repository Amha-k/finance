package com.saas.employee.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "employee_trainings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Training extends Base {

    @Column(nullable = false)
    private String trainingTitle;

    @Column(nullable = false)
    private String institution;

    @Column(nullable = false)
    private String sponsoredBy;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    private String fileName;
    private String fileType;
    @Lob
    @Column(length = 50000000)
    private byte[] fileBytes;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false, updatable = false)
    private Employee employee;
}
