package com.saas.employee.mapper;

import com.saas.employee.dto.request.FamilyRequest;
import com.saas.employee.dto.response.FamilyResponse;
import com.saas.employee.model.Employee;
import com.saas.employee.model.Family;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FamilyMapper {

    public Family mapToEntity(UUID tenantId,
                              Employee employee,
                              FamilyRequest request) {

        Family family = new Family();
        family.setTenantId(tenantId);
        family.setEmployee(employee);
        family.setRelationshipType(request.getRelationshipType());
        family.setFirstName(request.getFirstName());
        family.setMiddleName(request.getMiddleName());
        family.setLastName(request.getLastName());
        family.setDateOfBirth(request.getDateOfBirth());
        family.setGender(request.getGender());
        family.setHouseNumber(request.getHouseNumber());
        family.setHomeTelephone(request.getHomeTelephone());
        family.setOfficeTelephone(request.getOfficeTelephone());
        family.setMobileNumber(request.getMobileNumber());
        family.setEmail(request.getEmail());
        family.setPoBox(request.getPoBox());
        family.setEmergencyContact(request.isEmergencyContact());

        return family;
    }

    public FamilyResponse mapToDto(Family family) {

        FamilyResponse response = new FamilyResponse();
        response.setId(family.getId());
        response.setTenantId(family.getTenantId());
        response.setRelationshipType(family.getRelationshipType().name());
        response.setFirstName(family.getFirstName());
        response.setMiddleName(family.getMiddleName());
        response.setLastName(family.getLastName());
        response.setDateOfBirth(family.getDateOfBirth());
        response.setAge(Period.between(family.getDateOfBirth(), LocalDate.now()).getYears());
        response.setGender(family.getGender().name());
        response.setHouseNumber(family.getHouseNumber());
        response.setHomeTelephone(family.getHomeTelephone());
        response.setOfficeTelephone(family.getOfficeTelephone());
        response.setMobileNumber(family.getMobileNumber());
        response.setEmail(family.getEmail());
        response.setPoBox(family.getPoBox());
        response.setEmergencyContact(family.isEmergencyContact());
        response.setCreatedAt(family.getCreatedAt());
        response.setUpdatedAt(family.getUpdatedAt());
        response.setCreatedBy(family.getCreatedBy());
        response.setUpdatedBy(family.getUpdatedBy());
        response.setEmployeeId(family.getEmployee().getId());

        return response;
    }

    public Family mapUpdateRequest(Family family,
                               FamilyRequest request) {

        if (request.getRelationshipType() != null) {
            family.setRelationshipType(request.getRelationshipType());
        }
        if (request.getFirstName() != null) {
            family.setFirstName(request.getFirstName());
        }
        if (request.getMiddleName() != null) {
            family.setMiddleName(request.getMiddleName());
        }
        if (request.getLastName() != null) {
            family.setLastName(request.getLastName());
        }
        if (request.getDateOfBirth() != null) {
            family.setDateOfBirth(request.getDateOfBirth());
        }
        if (request.getGender() != null) {
            family.setGender(request.getGender());
        }
        if (request.getHouseNumber() != null) {
            family.setHouseNumber(request.getHouseNumber());
        }
        if (request.getHomeTelephone() != null) {
            family.setHomeTelephone(request.getHomeTelephone());
        }
        if (request.getOfficeTelephone() != null) {
            family.setOfficeTelephone(request.getOfficeTelephone());
        }
        if (request.getMobileNumber() != null) {
            family.setMobileNumber(request.getMobileNumber());
        }
        if (request.getEmail() != null) {
            family.setEmail(request.getEmail());
        }
        if (request.getPoBox() != null) {
            family.setPoBox(request.getPoBox());
        }

        family.setEmergencyContact(request.isEmergencyContact());

        return family;
    }
}
