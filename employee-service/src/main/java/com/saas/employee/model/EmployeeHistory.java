package com.saas.employee.model;

import com.saas.employee.enums.ProcessType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "employee_histories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeHistory extends Base {

    @Column(nullable = false)
    private UUID departmentId;

    @Column(nullable = false)
    private UUID jobId;

    @Column(nullable = false)
    private UUID payGradeId;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private ProcessType processType;

    @ManyToOne
    @JoinColumn(name = "duty_station_id", nullable = false, updatable = false)
    private DutyStation dutyStation;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false, updatable = false)
    private Employee employee;
}
