package com.saas.employee.service;

import com.saas.employee.dto.request.LanguageNameRequest;
import com.saas.employee.dto.response.LanguageNameResponse;
import com.saas.employee.exception.ResourceExistsException;
import com.saas.employee.mapper.LanguageNameMapper;
import com.saas.employee.model.LanguageName;
import com.saas.employee.repository.LanguageNameRepository;
import com.saas.employee.utility.ValidationUtil;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageNameService {

    private final LanguageNameRepository languageNameRepository;
    private final LanguageNameMapper languageNameMapper;
    private final ValidationUtil validationUtil;

    public LanguageNameResponse addLanguageName(UUID tenantId,
                                                LanguageNameRequest request) {

        LanguageName languageName = languageNameMapper.mapToEntity(tenantId, request);
        if (languageNameRepository
                .existsByLanguageNameAndTenantId(request.getLanguageName(), tenantId)) {
            throw new ResourceExistsException(
                    "Language with name '" + request.getLanguageName() + "' already exists");
        }
        languageName = languageNameRepository.save(languageName);
        return languageNameMapper.mapToDto(languageName);
    }

    public List<LanguageNameResponse> getAllLanguageNames(UUID tenantId) {

        List<LanguageName> languageNames = languageNameRepository.findAll();
        return languageNames.stream()
                .filter(language -> language.getTenantId().equals(tenantId))
                .map(languageNameMapper::mapToDto)
                .toList();
    }

    public LanguageNameResponse getLanguageNameById(UUID tenantId,
                                                    UUID languageId) {

        LanguageName languageName = validationUtil.getLanguageNameById(tenantId, languageId);
        return languageNameMapper.mapToDto(languageName);
    }

    public LanguageNameResponse updateLanguageName(UUID tenantId,
                                                   UUID languageId,
                                                   LanguageNameRequest request) {

        LanguageName languageName = validationUtil.getLanguageNameById(tenantId, languageId);
        if (languageNameRepository.existsByTenantIdAndLanguageNameAndIdNot(
                tenantId, request.getLanguageName(), languageName.getId())) {
            throw new ResourceExistsException(
                    "Language with name '" + request.getLanguageName() + "' already exists");
        }
        languageName = languageNameMapper.mapUpdateRequest(languageName, request);
        languageName = languageNameRepository.save(languageName);
        return languageNameMapper.mapToDto(languageName);
    }

    public void deleteLanguageName(UUID tenantId,
                                   UUID languageId) {

        LanguageName languageName = validationUtil.getLanguageNameById(tenantId, languageId);
        languageNameRepository.delete(languageName);
    }
}
