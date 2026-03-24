package com.saas.employee.repository;

import com.saas.employee.model.Reference;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferenceRepository extends JpaRepository<Reference, UUID> {

    List<Reference> findByEmployeeId(UUID empId);
}
