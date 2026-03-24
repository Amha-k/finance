package com.saas.employee.model;

import com.saas.employee.enums.AddressType;
import jakarta.persistence.*;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "addresses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address extends Base {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    @Column(nullable = false)
    private UUID locationId;

    private String houseNumber;
    private String homeTelephone;
    private String officeTelephone;

    @Column(nullable = false)
    private String mobileNumber;

    private String email;
    private String poBox;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false, updatable = false)
    private Employee employee;
}
