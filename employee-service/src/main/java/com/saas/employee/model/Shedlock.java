package com.saas.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
public class Shedlock {

    @Id
    private String name;

    private Instant lockUntil;
    private Instant lockedAt;
    private String lockedBy;
}
