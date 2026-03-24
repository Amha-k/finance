package com.saas.employee.client;

import com.saas.employee.dto.clientDto.*;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "organization-service", path = "/api/organization")
public interface OrganizationServiceClient {

    @GetMapping("/tenants/get/{id}")
    TenantDto getTenantById(@PathVariable UUID id);

    @GetMapping("/departments/{tenant-id}/get/{id}")
    DepartmentDto getDepartmentById(@PathVariable UUID id,
                                    @PathVariable("tenant-id") UUID tenantId);

    @GetMapping("/job-registrations/{tenant-id}/get/{id}")
    JobDto getJobById(@PathVariable UUID id,
                      @PathVariable("tenant-id") UUID tenantId);

    @GetMapping("/job-registrations/{tenant-id}/jobs/{departmentId}")
    List<JobDto> getAllJobsByTenantAndDepartment(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable("departmentId") UUID departmentId);

    @GetMapping("/pay-grades/{tenant-id}/get/{id}")
    PayGradeDto getPayGradeById(@PathVariable UUID id,
                                @PathVariable("tenant-id") UUID tenantId);

    @GetMapping("/pay-grades/{tenant-id}/jobgrade/{jobGradeId}")
    List<PayGradeDto> getPayGradesByJobGradeId(@PathVariable UUID jobGradeId,
                                               @PathVariable("tenant-id") UUID tenantId);

    @GetMapping("/locations/{tenant-id}/get/{id}")
    LocationDto getLocationById(@PathVariable UUID id,
                                @PathVariable("tenant-id") UUID tenantId);

    @GetMapping("/field-of-studies/{tenant-id}/get/{id}")
    FieldOfStudyDto getFieldOfStudyById(@PathVariable UUID id,
                                        @PathVariable("tenant-id") UUID tenantId);

    @GetMapping("/education-levels/{tenant-id}/get/{id}")
    EducationLevelDto getEducationLevelById(@PathVariable UUID id,
                                            @PathVariable("tenant-id") UUID tenantId);
}
