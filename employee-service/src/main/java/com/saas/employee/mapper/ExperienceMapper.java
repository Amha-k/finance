package com.saas.employee.mapper;

import com.saas.employee.dto.request.ExperienceRequest;
import com.saas.employee.dto.response.ExperienceResponse;
import com.saas.employee.model.Experience;
import com.saas.employee.model.Employee;
import com.saas.employee.utility.FileUtil;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ExperienceMapper {

    public Experience mapToEntity(UUID tenantId,
                                  Employee employee,
                                  ExperienceRequest request,
                                  MultipartFile file) throws IOException {

        Experience experience = new Experience();
        experience.setTenantId(tenantId);
        experience.setEmployee(employee);
        experience.setInstitution(request.getInstitution());
        experience.setEmploymentType(request.getEmploymentType());
        experience.setJobTitle(request.getJobTitle());
        experience.setSalary(request.getSalary());
        experience.setStartDate(request.getStartDate());
        experience.setEndDate(request.getEndDate());
        experience.setResponsibility(request.getResponsibility());
        experience.setReasonForTermination(request.getReasonForTermination());

        experience.setDuration(calculateDuration(request.getStartDate(), request.getEndDate()));

        return getExperienceFile(experience, file);
    }

    public ExperienceResponse mapToDto(Experience experience) {

        ExperienceResponse response = new ExperienceResponse();
        response.setId(experience.getId());
        response.setTenantId(experience.getTenantId());
        response.setInstitution(experience.getInstitution());
        response.setEmploymentType(experience.getEmploymentType().name());
        response.setJobTitle(experience.getJobTitle());
        response.setSalary(experience.getSalary());
        response.setStartDate(experience.getStartDate());
        response.setEndDate(experience.getEndDate());
        response.setDuration(experience.getDuration());
        response.setResponsibility(experience.getResponsibility());
        response.setReasonForTermination(experience.getReasonForTermination());
        response.setFileName(experience.getFileName());
        response.setFileType(experience.getFileType());
        response.setFileBytes(experience.getFileBytes());
        response.setCreatedAt(experience.getCreatedAt());
        response.setUpdatedAt(experience.getUpdatedAt());
        response.setCreatedBy(experience.getCreatedBy());
        response.setUpdatedBy(experience.getUpdatedBy());
        response.setEmployeeId(experience.getEmployee().getId());

        return response;
    }

    public Experience mapUpdateRequest(Experience experience,
                                       ExperienceRequest request,
                                       MultipartFile file) throws IOException {

        if (request.getInstitution() != null) {
            experience.setInstitution(request.getInstitution());
        }
        if (request.getEmploymentType() != null) {
            experience.setEmploymentType(request.getEmploymentType());
        }
        if (request.getJobTitle() != null) {
            experience.setJobTitle(request.getJobTitle());
        }
        if (request.getSalary() != null) {
            experience.setSalary(request.getSalary());
        }
        if (request.getStartDate() != null) {
            experience.setStartDate(request.getStartDate());
        }
        if (request.getEndDate() != null) {
            experience.setEndDate(request.getEndDate());
        }
        if (request.getStartDate() != null) {
            experience.setStartDate(request.getStartDate());
        }
        if (request.getResponsibility() != null) {
            experience.setResponsibility(request.getResponsibility());
        }
        if (request.getReasonForTermination() != null) {
            experience.setReasonForTermination(request.getReasonForTermination());
        }

        experience.setDuration(calculateDuration(request.getStartDate(), request.getEndDate()));

        return getExperienceFile(experience, file);
    }

    private Experience getExperienceFile(Experience experience,
                                         MultipartFile file) throws IOException {

        if (file != null && !file.isEmpty()) {
            String contentType = file.getContentType();
            boolean isPdf = MediaType.APPLICATION_PDF_VALUE.equals(contentType);
            boolean isDocx = "application/vnd.openxmlformats-officedocument.wordprocessingml.document".equals(contentType);
            boolean isImage = contentType != null && contentType.startsWith("image/");

            if (!(isPdf || isDocx || isImage)) {
                throw new IllegalArgumentException("Only PDF, DOCX, and image files are allowed!");
            }
            experience.setFileName(file.getOriginalFilename());
            experience.setFileType(file.getContentType());
            experience.setFileBytes(FileUtil.compressFile(file.getBytes()));
        }

        return experience;
    }

    private static String calculateDuration(LocalDate startDate, LocalDate endDate) {

        Period period = Period.between(startDate, endDate);
        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();

        String daysString = (days > 1) ? "days" : "day";

        return years + " year, " + months + " month and " + days + " " + daysString;
    }
}
