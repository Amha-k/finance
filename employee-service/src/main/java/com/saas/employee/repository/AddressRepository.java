package com.saas.employee.repository;

import com.saas.employee.model.Address;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {

    List<Address> findByEmployeeId(UUID employeeId);
}
