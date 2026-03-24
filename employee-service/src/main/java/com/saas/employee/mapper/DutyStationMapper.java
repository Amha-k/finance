package com.saas.employee.mapper;

import com.saas.employee.dto.request.DutyStationRequest;
import com.saas.employee.dto.response.DutyStationResponse;
import com.saas.employee.model.DutyStation;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DutyStationMapper {

    public DutyStation mapToEntity(UUID tenantId,
                                   DutyStationRequest request) {

        DutyStation dutyStation = new DutyStation();
        dutyStation.setName(request.getName());
        dutyStation.setDescription(request.getDescription());
        dutyStation.setTenantId(tenantId);

        return dutyStation;
    }

    public DutyStationResponse mapToDto(DutyStation dutyStation) {

        DutyStationResponse response = new DutyStationResponse();
        response.setId(dutyStation.getId());
        response.setName(dutyStation.getName());
        response.setDescription(dutyStation.getDescription());
        response.setTenantId(dutyStation.getTenantId());
        response.setCreatedAt(dutyStation.getCreatedAt());
        response.setUpdatedAt(dutyStation.getUpdatedAt());
        response.setCreatedBy(dutyStation.getCreatedBy());
        response.setUpdatedBy(dutyStation.getUpdatedBy());

        return response;
    }

    public DutyStation mapUpdateRequest(DutyStation dutyStation,
                                    DutyStationRequest request) {

        if (request.getName() != null) {
            dutyStation.setName(request.getName());
        }

        if (request.getDescription() != null) {
            dutyStation.setDescription(request.getDescription());
        }

        return dutyStation;
    }
}
