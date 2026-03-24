package com.saas.employee.service;

import com.saas.employee.dto.request.ExperienceRequest;
import com.saas.employee.dto.response.ExperienceResponse;
import com.saas.employee.exception.ResourceNotFoundException;
import com.saas.employee.mapper.ExperienceMapper;
import com.saas.employee.model.Employee;
import com.saas.employee.model.Experience;
import com.saas.employee.repository.ExperienceRepository;
import com.saas.employee.utility.FileUtil;
import com.saas.employee.utility.ValidationUtil;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final ExperienceMapper experienceMapper;
    private final ValidationUtil validationUtil;

    public ExperienceResponse addExperience(UUID tenantId,
                                            UUID employeeId,
                                            ExperienceRequest request,
                                            MultipartFile file) throws IOException {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        Experience experience = experienceMapper.mapToEntity(tenantId, employee, request, file);
        experience = experienceRepository.save(experience);
        return experienceMapper.mapToDto(experience);
    }

    public List<ExperienceResponse> getAllExperiences(UUID tenantId,
                                                      UUID employeeId) {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        List<Experience> experiences = experienceRepository.findByEmployeeId(employee.getId());
        return experiences.stream()
                .filter(exp -> exp.getTenantId().equals(tenantId))
                .map(experienceMapper::mapToDto)
                .toList();
    }

    public List<ExperienceResponse> getEmployeeExperiences(UUID tenantId,
                                                           String employeeId) {

        Employee employee = validationUtil.getEmployeeByEmployeeId(tenantId, employeeId);
        List<Experience> experiences = experienceRepository.findByEmployeeId(employee.getId());
        return experiences.stream()
                .filter(exp -> exp.getTenantId().equals(tenantId))
                .map(experienceMapper::mapToDto)
                .toList();
    }

    public ExperienceResponse getExperienceById(UUID tenantId,
                                                UUID employeeId,
                                                UUID experienceId) {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        Experience experience = getExperienceById(tenantId, employee, experienceId);
        return experienceMapper.mapToDto(experience);
    }

    public byte[] getExperienceDocumentById(UUID tenantId,
                                            UUID employeeId,
                                            UUID experienceId) {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        Experience experience = getExperienceById(tenantId, employee, experienceId);
        byte[] fileBytes = FileUtil.decompressFile(experience.getFileBytes());
        if (fileBytes.length == 0) {
            throw new ResourceNotFoundException("Experience file is not available");
        }
        return fileBytes;
    }

    public ExperienceResponse updateExperience(UUID tenantId,
                                               UUID employeeId,
                                               UUID experienceId,
                                               ExperienceRequest request,
                                               MultipartFile file) throws IOException {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        Experience experience = getExperienceById(tenantId, employee, experienceId);
        experience = experienceMapper.mapUpdateRequest(experience, request, file);
        experience = experienceRepository.save(experience);
        return experienceMapper.mapToDto(experience);
    }

    public void deleteExperience(UUID tenantId,
                                 UUID employeeId,
                                 UUID experienceId) {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        Experience experience = getExperienceById(tenantId, employee, experienceId);
        experienceRepository.delete(experience);
    }

    private Experience getExperienceById(UUID tenantId,
                                         Employee employee,
                                         UUID experienceId) {

        return experienceRepository
                .findById(experienceId)
                .filter(exp -> exp.getTenantId().equals(tenantId))
                .filter(exp -> exp.getEmployee().equals(employee))
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Experience not found with id '" + experienceId + "'"));
    }
}
