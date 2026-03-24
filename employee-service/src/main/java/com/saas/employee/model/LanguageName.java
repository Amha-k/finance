package com.saas.employee.model;

import jakarta.persistence.*;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "languages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageName extends Base {

    @Column(nullable = false)
    private String languageName;

    private String description;

    @OneToMany(mappedBy = "languageName")
    private Set<Language> languages;
}
