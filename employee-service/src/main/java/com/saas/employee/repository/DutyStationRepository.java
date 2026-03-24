package com.saas.employee.repository;

import com.saas.employee.model.DutyStation;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DutyStationRepository extends JpaRepository<DutyStation, UUID> {

    boolean existsByTenantIdAndName(UUID tenantId, String name);

    boolean existsByTenantIdAndNameAndIdNot(UUID tenantId, String name, UUID id);

    List<DutyStation> findByTenantId(UUID tenantId);
}
