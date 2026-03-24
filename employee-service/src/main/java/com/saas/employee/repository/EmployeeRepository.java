package com.saas.employee.repository;

import com.saas.employee.enums.EmploymentType;
import com.saas.employee.model.DutyStation;
import com.saas.employee.model.Employee;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    List<Employee> findByTenantIdAndDepartmentId(UUID tenantId, UUID depId);

    List<Employee> findByTenantIdAndEmploymentType(UUID tenantId, EmploymentType employmentType);

    boolean existsByTenantIdAndEmployeeId(UUID tenantId, String employeeId);

    boolean existsByTenantIdAndEmployeeIdAndIdNot(UUID tenantId, String employeeId, UUID id);

    Optional<Employee> findByTenantIdAndEmployeeId(UUID tenantId, String employeeId);

    Optional<Employee> findByTenantIdAndFirstNameAndMiddleNameAndLastName(
            UUID tenantId, String firstName, String middleName, String lastName);

    List<Employee> findByTenantIdAndDutyStation(UUID tenantId, DutyStation dutyStation);

    boolean existsByTenantIdAndEmail(UUID tenantId, String email);

    Optional<Employee> findByTenantIdAndId(UUID tenantId, UUID id);



    @Query("select e from Employee e where " +
            "e.tenantId = :tenantId AND " +
            "(lower(e.employeeId) like lower(concat('%', :keyword, '%')) OR " +
            "lower(e.firstName) like lower(concat('%', :keyword, '%')) OR " +
            "lower(e.middleName) like lower(concat('%', :keyword, '%')) OR " +
            "lower(e.lastName) like lower(concat('%', :keyword, '%')))")
    List<Employee> searchEmployees(@Param("tenantId") UUID tenantId,
                                   @Param("keyword") String keyword);

    List<Employee> findByTenantIdAndShiftId(UUID tenantId, UUID shiftId);
}
