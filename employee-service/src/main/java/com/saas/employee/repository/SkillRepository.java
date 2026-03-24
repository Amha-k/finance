package com.saas.employee.repository;

import com.saas.employee.model.Skill;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, UUID> {

    List<Skill> findByEmployeeId(UUID empId);
}
