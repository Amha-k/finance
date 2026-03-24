package com.saas.employee.repository;

import com.saas.employee.enums.ProcessType;
import com.saas.employee.model.EmployeeHistory;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeHistoryRepository extends JpaRepository<EmployeeHistory, UUID> {

    List<EmployeeHistory> findByTenantIdAndEmployeeId(UUID tenantId, UUID employeeId);
}
