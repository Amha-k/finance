package com.saas.employee.repository;

import com.saas.employee.model.Experience;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, UUID> {

    List<Experience> findByEmployeeId(UUID empId);
}
