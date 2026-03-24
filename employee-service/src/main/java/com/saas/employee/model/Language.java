package com.saas.employee.model;

import com.saas.employee.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "employee_languages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Language extends Base {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Listening listening;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Listening speaking;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Writing reading;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Writing writing;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false, updatable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "language_name_id", nullable = false)
    private LanguageName languageName;
}
