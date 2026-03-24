
package com.saas.employee.data;

import com.saas.employee.dto.eventDto.ResourceEvent;
import com.saas.employee.enums.ResourceName;
import com.saas.employee.enums.ResourceStatus;
import com.saas.employee.mapper.ResourceMapper;
import com.saas.employee.model.Resource;
import com.saas.employee.repository.ResourceRepository;
import java.util.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResourceData {

    private final ResourceRepository resourceRepository;
    private final ResourceMapper resourceMapper;

    public void saveResource(ResourceEvent eventResponse) {

        Set<Resource> resources = new HashSet<>();

        String defaultRole = "default_role";

        /* Resource */
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_ALL_RESOURCES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_RESOURCES_BY_ROLE_NAME.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_RESOURCE_BY_ID.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_RESOURCE_BY_NAME.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GRANT_RESOURCE_ACCESS_TO_ROLE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .REVOKE_RESOURCE_ACCESS_FROM_ROLE.getValue(), null, eventResponse));

        /* Employee */
        resources.add(resourceMapper.mapToEntity(ResourceName
                .ADD_EMPLOYEE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_ALL_EMPLOYEES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_EMPLOYEE_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_EMPLOYEE_BY_NAME.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_EMPLOYEE_BY_EMPLOYEE_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .DOWNLOAD_EMPLOYEE_IMAGE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_EMPLOYEES_BY_DEPARTMENT_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_EMPLOYEES_BY_SHIFT_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_EMPLOYEES_BY_EMPLOYMENT_TYPE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_EMPLOYEES_BY_DUTY_STATION.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .ADD_EMPLOYEE_HISTORY.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_EMPLOYEE_HISTORIES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .UPDATE_EMPLOYEE_EMAIL.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .UPDATE_EMPLOYEES_SHIFT.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .UPDATE_EMPLOYEE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .DELETE_EMPLOYEE.getValue(), null, eventResponse));

        /* Employee Training or Certificate */
        resources.add(resourceMapper.mapToEntity(ResourceName
                .ADD_TRAINING.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_ALL_TRAININGS.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_TRAINING_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_TRAININGS_BY_EMPLOYEE_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .DOWNLOAD_TRAINING_CERTIFICATE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .UPDATE_TRAINING.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .DELETE_TRAINING.getValue(), null, eventResponse));

        /* Title Name */
        resources.add(resourceMapper.mapToEntity(ResourceName
                .ADD_TITLE_NAME.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_ALL_TITLE_NAMES.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_TITLE_NAME_BY_ID.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .UPDATE_TITLE_NAME.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .DELETE_TITLE_NAME.getValue(), null, eventResponse));

        /* Employee Skill */
        resources.add(resourceMapper.mapToEntity(ResourceName
                .ADD_SKILL.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_ALL_SKILLS.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_SKILL_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_SKILLS_BY_EMPLOYEE_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .UPDATE_SKILL.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .DELETE_SKILL.getValue(), null, eventResponse));

        /* Employee Reference */
        resources.add(resourceMapper.mapToEntity(ResourceName
                .ADD_REFERENCE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_ALL_REFERENCES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_REFERENCE_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_REFERENCES_BY_EMPLOYEE_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .UPDATE_REFERENCE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .DELETE_REFERENCE.getValue(), null, eventResponse));

        /* Employee Language */
        resources.add(resourceMapper.mapToEntity(ResourceName
                .ADD_LANGUAGE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_ALL_LANGUAGES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_LANGUAGE_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_LANGUAGES_BY_EMPLOYEE_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .UPDATE_LANGUAGE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .DELETE_LANGUAGE.getValue(), null, eventResponse));

        /* Language Name */
        resources.add(resourceMapper.mapToEntity(ResourceName
                .ADD_LANGUAGE_NAME.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_ALL_LANGUAGE_NAMES.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_LANGUAGE_NAME_BY_ID.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .UPDATE_LANGUAGE_NAME.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .DELETE_LANGUAGE_NAME.getValue(), null, eventResponse));

        /* Employee Family */
        resources.add(resourceMapper.mapToEntity(ResourceName
                .ADD_FAMILY.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_ALL_FAMILIES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_FAMILY_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_FAMILIES_BY_EMPLOYEE_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .UPDATE_FAMILY.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .DELETE_FAMILY.getValue(), null, eventResponse));

        /* Employee Experience */
        resources.add(resourceMapper.mapToEntity(ResourceName
                .ADD_EXPERIENCE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_ALL_EXPERIENCES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_EXPERIENCE_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_EXPERIENCES_BY_EMPLOYEE_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .DOWNLOAD_EXPERIENCE_DOCUMENT.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .UPDATE_EXPERIENCE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .DELETE_EXPERIENCE.getValue(), null, eventResponse));

        /* Employee Education */
        resources.add(resourceMapper.mapToEntity(ResourceName
                .ADD_EDUCATION.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_ALL_EDUCATIONS.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_EDUCATION_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_EDUCATIONS_BY_EMPLOYEE_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .DOWNLOAD_EDUCATION_DOCUMENT.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .UPDATE_EDUCATION.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .DELETE_EDUCATION.getValue(), null, eventResponse));

        /* Employee Address */
        resources.add(resourceMapper.mapToEntity(ResourceName
                .ADD_ADDRESS.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_ALL_ADDRESSES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_ADDRESS_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_ADDRESSES_BY_EMPLOYEE_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .UPDATE_ADDRESS.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .DELETE_ADDRESS.getValue(), null, eventResponse));

        /* Duty Station */
        resources.add(resourceMapper.mapToEntity(ResourceName
                .ADD_DUTY_STATION.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_ALL_DUTY_STATIONS.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_DUTY_STATION_BY_ID.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .UPDATE_DUTY_STATION.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .DELETE_DUTY_STATION.getValue(), null, eventResponse));

        /* Country */
        resources.add(resourceMapper.mapToEntity(ResourceName
                .ADD_COUNTRY.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_ALL_COUNTRIES.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_COUNTRY_BY_ID.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .UPDATE_COUNTRY.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .DELETE_COUNTRY.getValue(), null, eventResponse));

        List<Resource> existedResources = resourceRepository.findByTenantId(eventResponse.getTenantId());
        resourceRepository.deleteAll(existedResources);
        resourceRepository.saveAll(resources);
    }
}
