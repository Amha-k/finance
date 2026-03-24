package com.saas.employee.repository;

import com.saas.employee.model.Family;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends JpaRepository<Family, UUID> {

    List<Family> findByEmployeeId(UUID empId);
}
