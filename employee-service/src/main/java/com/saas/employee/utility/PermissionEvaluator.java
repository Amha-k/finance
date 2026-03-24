package com.saas.employee.utility;

import com.saas.employee.enums.ResourceName;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PermissionEvaluator {

    private final PermissionUtil permissionUtil;

    /* Resource Permission */
    public void getAllResourcesPermission(UUID tenantId) {
        boolean isAdmin = permissionUtil.isAdmin();
        if (!isAdmin) {
            checkPermission(tenantId, ResourceName.GET_ALL_RESOURCES);
        }
    }

    public void getResourcesByRoleNamePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_RESOURCES_BY_ROLE_NAME);
    }

    public void getResourceByIdPermission(UUID tenantId) {
        boolean isAdmin = permissionUtil.isAdmin();
        if (!isAdmin) {
            checkPermission(tenantId, ResourceName.GET_RESOURCE_BY_ID);
        }
    }

    public void getResourceByNamePermission(UUID tenantId) {
        boolean isAdmin = permissionUtil.isAdmin();
        if (!isAdmin) {
            permissionUtil.isTenantUser(tenantId);
        }
    }

    public void grantResourceAccessToRolePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GRANT_RESOURCE_ACCESS_TO_ROLE);
    }

    public void revokeResourceAccessFromRolePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.REVOKE_RESOURCE_ACCESS_FROM_ROLE);
    }

    /* Employee Permissions */
    public void addEmployeePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_EMPLOYEE);
    }

    public void getAllEmployeesPermission(UUID tenantId) {
        boolean hasAddEmployeePermission = permissionUtil
                .hasPermission(tenantId, ResourceName.ADD_EMPLOYEE.getValue());
        if (!hasAddEmployeePermission) {
            checkPermission(tenantId, ResourceName.GET_ALL_EMPLOYEES);
        }
    }

    public void getEmployeeByIdPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.GET_EMPLOYEE_BY_ID, employeeId);
    }

    public void getEmployeeByNamePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_EMPLOYEE_BY_NAME);
    }

    public void getEmployeeByEmployeeIdPermission(UUID tenantId, String employeeId) {
        boolean isEmployeeHimself = permissionUtil.isEmployeeHimself(tenantId, null, employeeId);
        if (!isEmployeeHimself) {
            checkPermission(tenantId, ResourceName.GET_EMPLOYEE_BY_EMPLOYEE_ID);
        }
    }

    public void downloadEmployeeImagePermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.DOWNLOAD_EMPLOYEE_IMAGE, employeeId);
    }

    public void getEmployeesByDepartmentIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_EMPLOYEES_BY_DEPARTMENT_ID);
    }

    public void getEmployeesByShiftIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_EMPLOYEES_BY_SHIFT_ID);
    }

    public void getEmployeesByEmploymentTypePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_EMPLOYEES_BY_EMPLOYMENT_TYPE);
    }

    public void getEmployeesByDutyStationPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_EMPLOYEES_BY_DUTY_STATION);
    }

    public void getEmployeesHistoriesPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.GET_EMPLOYEE_HISTORIES, employeeId);
    }

    public void updateEmployeePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_EMPLOYEE);
    }

    public void updateEmployeeEmailPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.UPDATE_EMPLOYEE_EMAIL, employeeId);
    }

    public void updateEmployeesShiftPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_EMPLOYEES_SHIFT);
    }

    public void deleteEmployeePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_EMPLOYEE);
    }

    /* Employee Training or Certificate Permissions */
    public void addTrainingPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.ADD_TRAINING, employeeId);
    }

    public void getAllTrainingsPermission(UUID tenantId, UUID employeeId) {
        boolean hasAddTrainingPermission = permissionUtil
                .hasPermission(tenantId, ResourceName.ADD_TRAINING.getValue());
        if (!hasAddTrainingPermission) {
            doubleCheckPermission(tenantId, ResourceName.GET_ALL_TRAININGS, employeeId);
        }
    }

    public void getTrainingByIdPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.GET_TRAINING_BY_ID, employeeId);
    }

    public void getTrainingsByEmployeeIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_TRAININGS_BY_EMPLOYEE_ID);
    }

    public void downloadTrainingCertificatePermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.DOWNLOAD_TRAINING_CERTIFICATE, employeeId);
    }

    public void updateTrainingPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.UPDATE_TRAINING, employeeId);
    }

    public void deleteTrainingPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.DELETE_TRAINING, employeeId);
    }

    /* Title Name Permissions */
    public void addTitleNamePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_TITLE_NAME);
    }

    public void getAllTitleNamesPermission(UUID tenantId) {
        boolean hasAddTitleNamePermission = permissionUtil
                .hasPermission(tenantId, ResourceName.ADD_TITLE_NAME.getValue());
        if (!hasAddTitleNamePermission) {
        checkPermission(tenantId, ResourceName.GET_ALL_TITLE_NAMES);
        }
    }

    public void getTitleNameByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_TITLE_NAME_BY_ID);
    }

    public void updateTitleNamePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_TITLE_NAME);
    }

    public void deleteTitleNamePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_TITLE_NAME);
    }

    /* Employee Skill Permissions */
    public void addSkillPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.ADD_SKILL, employeeId);
    }

    public void getAllSkillsPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.GET_ALL_SKILLS, employeeId);
    }

    public void getSkillByIdPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.GET_SKILL_BY_ID, employeeId);
    }

    public void getSkillsByEmployeeIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_SKILLS_BY_EMPLOYEE_ID);
    }

    public void updateSkillPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.UPDATE_SKILL, employeeId);
    }

    public void deleteSkillPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.DELETE_SKILL, employeeId);
    }

    /* Employee Reference Permissions */
    public void addReferencePermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.ADD_REFERENCE, employeeId);
    }

    public void getAllReferencesPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.GET_ALL_REFERENCES, employeeId);
    }

    public void getReferenceByIdPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.GET_REFERENCE_BY_ID, employeeId);
    }

    public void getReferencesByEmployeeIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_REFERENCES_BY_EMPLOYEE_ID);
    }

    public void updateReferencePermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.UPDATE_REFERENCE, employeeId);
    }

    public void deleteReferencePermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.DELETE_REFERENCE, employeeId);
    }

    /* Employee Language Permissions */
    public void addLanguagePermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.ADD_LANGUAGE, employeeId);
    }

    public void getAllLanguagesPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.GET_ALL_LANGUAGES, employeeId);
    }

    public void getLanguageByIdPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.GET_LANGUAGE_BY_ID, employeeId);
    }

    public void getLanguagesByEmployeeIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_LANGUAGES_BY_EMPLOYEE_ID);
    }

    public void updateLanguagePermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.UPDATE_LANGUAGE, employeeId);
    }

    public void deleteLanguagePermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.DELETE_LANGUAGE, employeeId);
    }

    /* Language Name Permissions */
    public void addLanguageNamePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_LANGUAGE_NAME);
    }

    public void getAllLanguageNamesPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_LANGUAGE_NAMES);
    }

    public void getLanguageNameByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_LANGUAGE_NAME_BY_ID);
    }

    public void updateLanguageNamePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_LANGUAGE_NAME);
    }

    public void deleteLanguageNamePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_LANGUAGE_NAME);
    }

    /* Employee Family Permissions */
    public void addFamilyPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.ADD_FAMILY, employeeId);
    }

    public void getAllFamiliesPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.GET_ALL_FAMILIES, employeeId);
    }

    public void getFamilyByIdPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.GET_FAMILY_BY_ID, employeeId);
    }

    public void getFamiliesByEmployeeIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_FAMILIES_BY_EMPLOYEE_ID);
    }

    public void updateFamilyPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.UPDATE_FAMILY, employeeId);
    }

    public void deleteFamilyPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.DELETE_FAMILY, employeeId);
    }

    /* Employee Experience Permissions */
    public void addExperiencePermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.ADD_EXPERIENCE, employeeId);
    }

    public void getAllExperiencesPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.GET_ALL_EXPERIENCES, employeeId);
    }

    public void getExperienceByIdPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.GET_EXPERIENCE_BY_ID, employeeId);
    }

    public void getExperiencesByEmployeeIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_EXPERIENCES_BY_EMPLOYEE_ID);
    }

    public void downloadExperienceDocumentPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.DOWNLOAD_EXPERIENCE_DOCUMENT, employeeId);
    }

    public void updateExperiencePermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.UPDATE_EXPERIENCE, employeeId);
    }

    public void deleteExperiencePermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.DELETE_EXPERIENCE, employeeId);
    }

    /* Employee Education Permissions */
    public void addEducationPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.ADD_EDUCATION, employeeId);
    }

    public void getAllEducationsPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.GET_ALL_EDUCATIONS, employeeId);
    }

    public void getEducationByIdPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.GET_EDUCATION_BY_ID, employeeId);
    }

    public void getEducationsByEmployeeIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_EDUCATIONS_BY_EMPLOYEE_ID);
    }

    public void downloadEducationDocumentPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.DOWNLOAD_EDUCATION_DOCUMENT, employeeId);
    }

    public void updateEducationPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.UPDATE_EDUCATION, employeeId);
    }

    public void deleteEducationPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.DELETE_EDUCATION, employeeId);
    }

    /* Employee Address Permissions */
    public void addAddressPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.ADD_ADDRESS, employeeId);
    }

    public void getAllAddressesPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.GET_ALL_ADDRESSES, employeeId);
    }

    public void getAddressByIdPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.GET_ADDRESS_BY_ID, employeeId);
    }

    public void getAddressesByEmployeeIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ADDRESSES_BY_EMPLOYEE_ID);
    }

    public void updateAddressPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.UPDATE_ADDRESS, employeeId);
    }

    public void deleteAddressPermission(UUID tenantId, UUID employeeId) {
        doubleCheckPermission(tenantId, ResourceName.DELETE_ADDRESS, employeeId);
    }

    /* Duty Station Permissions */
    public void addDutyStationPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_DUTY_STATION);
    }

    public void getAllDutyStationsPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_DUTY_STATIONS);
    }

    public void getDutyStationByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_DUTY_STATION_BY_ID);
    }

    public void updateDutyStationPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_DUTY_STATION);
    }

    public void deleteDutyStationPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_DUTY_STATION);
    }

    /* Country Permissions */
    public void addCountryPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_COUNTRY);
    }

    public void getAllCountriesPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_COUNTRIES);
    }

    public void getCountryByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_COUNTRY_BY_ID);
    }

    public void updateCountryPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_COUNTRY);
    }

    public void deleteCountryPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_COUNTRY);
    }

    private void checkPermission(UUID tenantId, ResourceName resourceName) {
        boolean hasPermission = permissionUtil.hasPermission(tenantId, resourceName.getValue());
        if (!hasPermission) {
            throw new AccessDeniedException("Access Denied");
        }
    }

    private void doubleCheckPermission(UUID tenantId, ResourceName resourceName, UUID employeeId) {

        boolean hasPermission = permissionUtil.hasPermission(tenantId, resourceName.getValue());
        if (!hasPermission) {
            permissionUtil.isEmployeeHimself(tenantId, employeeId, null);
        }
    }
}
