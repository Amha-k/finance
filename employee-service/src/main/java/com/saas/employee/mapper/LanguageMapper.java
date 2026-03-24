package com.saas.employee.mapper;

import com.saas.employee.dto.request.LanguageRequest;
import com.saas.employee.dto.response.LanguageResponse;
import com.saas.employee.model.Employee;
import com.saas.employee.model.Language;
import com.saas.employee.model.LanguageName;
import com.saas.employee.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class LanguageMapper {

    private final ValidationUtil validationUtil;

    public Language mapToEntity(UUID tenantId,
                                Employee employee,
                                LanguageRequest request) {

        LanguageName languageName = validationUtil
                .getLanguageNameById(tenantId, request.getLanguageNameId());

        Language language = new Language();
        language.setTenantId(tenantId);
        language.setEmployee(employee);
        language.setLanguageName(languageName);
        language.setListening(request.getListening());
        language.setSpeaking(request.getSpeaking());
        language.setWriting(request.getWriting());
        language.setReading(request.getReading());

        return language;
    }

    public LanguageResponse mapToDto(Language language) {

        LanguageResponse response = new LanguageResponse();
        response.setId(language.getId());
        response.setTenantId(language.getTenantId());
        response.setLanguageNameId(language.getLanguageName().getId());
        response.setListening(language.getListening().name());
        response.setSpeaking(language.getSpeaking().name());
        response.setWriting(language.getWriting().name());
        response.setReading(language.getReading().name());
        response.setCreatedAt(language.getCreatedAt());
        response.setUpdatedAt(language.getUpdatedAt());
        response.setCreatedBy(language.getCreatedBy());
        response.setUpdatedBy(language.getUpdatedBy());
        response.setEmployeeId(language.getEmployee().getId());

        return response;
    }

    public Language mapUpdateRequest(UUID tenantId,
                                   Language language,
                                   LanguageRequest request) {

        LanguageName languageName = validationUtil
                .getLanguageNameById(tenantId, request.getLanguageNameId());

        if (request.getLanguageNameId() != null) {
            language.setLanguageName(languageName);
        }
        if (request.getListening() != null) {
            language.setListening(request.getListening());
        }
        if (request.getSpeaking() != null) {
            language.setSpeaking(request.getSpeaking());
        }
        if (request.getWriting() != null) {
            language.setWriting(request.getWriting());
        }
        if (request.getReading() != null) {
            language.setReading(request.getReading());
        }

        return language;
    }
}
