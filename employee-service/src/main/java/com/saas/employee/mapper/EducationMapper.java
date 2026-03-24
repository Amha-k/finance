package com.saas.employee.mapper;

import com.saas.employee.dto.clientDto.EducationLevelDto;
import com.saas.employee.dto.clientDto.FieldOfStudyDto;
import com.saas.employee.dto.request.EducationRequest;
import com.saas.employee.dto.response.EducationResponse;
import com.saas.employee.model.Education;
import com.saas.employee.model.Employee;
import com.saas.employee.utility.FileUtil;
import com.saas.employee.utility.ValidationUtil;
import java.io.IOException;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class EducationMapper {

    private final ValidationUtil validationUtil;

    public Education mapToEntity(UUID tenantId,
                                 Employee employee,
                                 EducationRequest request,
                                 MultipartFile file) throws IOException {

        EducationLevelDto educationLevel = validationUtil
                .getEducationLevelById(tenantId, request.getEducationLevelId());
        FieldOfStudyDto fieldOfStudy = validationUtil
                .getFieldOfStudyById(tenantId, request.getFieldOfStudyId());

        Education education = new Education();
        education.setTenantId(tenantId);
        education.setEmployee(employee);
        education.setEducationLevelId(educationLevel.getId());
        education.setFieldOfStudyId(fieldOfStudy.getId());
        education.setEducationType(request.getEducationType());
        education.setInstitution(request.getInstitution());
        education.setStartDate(request.getStartDate());
        education.setEndDate(request.getEndDate());
        education.setAward(request.getAward());
        education.setResult(request.getResult());

        return getEducationFile(education, file);
    }

    public EducationResponse mapToDto(Education education) {

        EducationResponse response = new EducationResponse();
        response.setId(education.getId());
        response.setTenantId(education.getTenantId());
        response.setEducationLevelId(education.getEducationLevelId());
        response.setEducationType(education.getEducationType().name());
        response.setFieldOfStudyId(education.getFieldOfStudyId());
        response.setInstitution(education.getInstitution());
        response.setStartDate(education.getStartDate());
        response.setEndDate(education.getEndDate());
        response.setAward(education.getAward());
        response.setResult(education.getResult());
        response.setFileName(education.getFileName());
        response.setFileType(education.getFileType());
        response.setFileBytes(education.getFileBytes());
        response.setCreatedAt(education.getCreatedAt());
        response.setUpdatedAt(education.getUpdatedAt());
        response.setCreatedBy(education.getCreatedBy());
        response.setUpdatedBy(education.getUpdatedBy());
        response.setEmployeeId(education.getEmployee().getId());

        return response;
    }

    public Education mapUpdateRequest(UUID tenantId,
                                     Education education,
                                     EducationRequest request,
                                     MultipartFile file) throws IOException {

        EducationLevelDto educationLevel = validationUtil
                .getEducationLevelById(tenantId, request.getEducationLevelId());
        FieldOfStudyDto fieldOfStudy = validationUtil
                .getFieldOfStudyById(tenantId, request.getFieldOfStudyId());

        if (request.getEducationLevelId() != null) {
            education.setEducationLevelId(educationLevel.getId());
        }
        if (request.getFieldOfStudyId() != null) {
            education.setFieldOfStudyId(fieldOfStudy.getId());
        }
        if (request.getEducationType() != null) {
            education.setEducationType(request.getEducationType());
        }
        if (request.getInstitution() != null) {
            education.setInstitution(request.getInstitution());
        }
        if (request.getStartDate() != null) {
            education.setStartDate(request.getStartDate());
        }
        if (request.getEndDate() != null) {
            education.setEndDate(request.getEndDate());
        }
        if (request.getAward() != null) {
            education.setAward(request.getAward());
        }
        if (request.getResult() != null) {
            education.setResult(request.getResult());
        }
        return getEducationFile(education, file);
    }

    private Education getEducationFile(Education education,
                                       MultipartFile file) throws IOException {

        if (file != null && !file.isEmpty()) {
            String contentType = file.getContentType();
            boolean isPdf = MediaType.APPLICATION_PDF_VALUE.equals(contentType);
            boolean isDocx = "application/vnd.openxmlformats-officedocument.wordprocessingml.document".equals(contentType);
            boolean isImage = contentType != null && contentType.startsWith("image/");

            if (!(isPdf || isDocx || isImage)) {
                throw new IllegalArgumentException("Only PDF, DOCX, and image files are allowed!");
            }
            education.setFileName(file.getOriginalFilename());
            education.setFileType(file.getContentType());
            education.setFileBytes(FileUtil.compressFile(file.getBytes()));
        }

        return education;
    }
}
