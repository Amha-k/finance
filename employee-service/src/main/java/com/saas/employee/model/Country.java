package com.saas.employee.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Set;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "countries")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country extends Base {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String abbreviatedName;

    @Column(nullable = false)
    private String code;

    @OneToMany(mappedBy = "country")
    private Set<Employee> employees;
}
