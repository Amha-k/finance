package com.saas.employee.repository;

import com.saas.employee.model.EmployeeEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeEventRepository extends JpaRepository<EmployeeEvent, UUID> {
}
