package com.saas.employee.repository;

import com.saas.employee.model.TitleName;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleNameRepository extends JpaRepository<TitleName, UUID> {

    boolean existsByTitleNameAndTenantId(String titleName, UUID tenantId);

    boolean existsByTenantIdAndTitleNameAndIdNot(UUID tenantId, String titleName, UUID id);
}
