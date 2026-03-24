package com.saas.employee.repository;

import com.saas.employee.model.Training;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<Training, UUID> {

    List<Training> findByEmployeeId(UUID empId);
}
