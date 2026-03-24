package com.saas.employee.service;

import com.saas.employee.dto.request.LanguageRequest;
import com.saas.employee.dto.response.LanguageResponse;
import com.saas.employee.exception.ResourceNotFoundException;
import com.saas.employee.mapper.LanguageMapper;
import com.saas.employee.model.Employee;
import com.saas.employee.model.Language;
import com.saas.employee.repository.LanguageRepository;
import com.saas.employee.utility.ValidationUtil;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageService {

    private final LanguageRepository languageRepository;
    private final LanguageMapper languageMapper;
    private final ValidationUtil validationUtil;

    public LanguageResponse addLanguage(UUID tenantId,
                                        UUID employeeId,
                                        LanguageRequest request) {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        Language language = languageMapper.mapToEntity(tenantId, employee, request);
        language = languageRepository.save(language);
        return languageMapper.mapToDto(language);
    }

    public List<LanguageResponse> getAllLanguages(UUID tenantId,
                                                  UUID employeeId) {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        List<Language> languages = languageRepository.findByEmployeeId(employee.getId());
        return languages.stream()
                .filter(lan -> lan.getTenantId().equals(tenantId))
                .map(languageMapper::mapToDto)
                .toList();
    }

    public List<LanguageResponse> getEmployeeLanguages(UUID tenantId,
                                                       String employeeId) {

        Employee employee = validationUtil.getEmployeeByEmployeeId(tenantId, employeeId);
        List<Language> languages = languageRepository.findByEmployeeId(employee.getId());
        return languages.stream()
                .filter(lan -> lan.getTenantId().equals(tenantId))
                .map(languageMapper::mapToDto)
                .toList();
    }

    public LanguageResponse getLanguageById(UUID tenantId,
                                            UUID employeeId,
                                            UUID languageId) {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        Language language = getLanguageById(tenantId, employee, languageId);
        return languageMapper.mapToDto(language);
    }

    public LanguageResponse updateLanguage(UUID tenantId,
                                           UUID employeeId,
                                           UUID languageId,
                                           LanguageRequest request) {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        Language language = getLanguageById(tenantId, employee, languageId);
        language = languageMapper.mapUpdateRequest(tenantId, language, request);
        language = languageRepository.save(language);
        return languageMapper.mapToDto(language);
    }

    public void deleteLanguage(UUID tenantId,
                               UUID employeeId,
                               UUID languageId) {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        Language language = getLanguageById(tenantId, employee, languageId);
        languageRepository.delete(language);
    }

    private Language getLanguageById(UUID tenantId,
                                     Employee employee,
                                     UUID languageId) {

        return languageRepository
                .findById(languageId)
                .filter(lan -> lan.getTenantId().equals(tenantId))
                .filter(lan -> lan.getEmployee().equals(employee))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Language not found with id '" + languageId + "'"));
    }
}
