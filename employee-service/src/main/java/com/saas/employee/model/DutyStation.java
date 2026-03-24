package com.saas.employee.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "duty_stations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DutyStation extends Base {

    @Column(nullable = false)
    @Size(min = 2)
    private String name;

    private String description;

    @OneToMany(mappedBy = "dutyStation")
    private Set<Employee> employees;

    @OneToMany(mappedBy = "dutyStation")
    private Set<EmployeeHistory> employeeHistories;
}
