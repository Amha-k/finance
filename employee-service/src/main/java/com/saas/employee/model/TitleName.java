package com.saas.employee.model;

import jakarta.persistence.*;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "title_names")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TitleName extends Base {

    @Column(nullable = false)
    private String titleName;

    private String description;

    @OneToMany(mappedBy = "titleName")
    private List<Employee> employees;
}
