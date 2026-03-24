package com.saas.employee.repository;

import com.saas.employee.model.Education;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education, UUID> {

    List<Education> findByEmployeeId(UUID empId);
}
