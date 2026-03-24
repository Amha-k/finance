package com.saas.employee.service;

import com.saas.employee.dto.request.DutyStationRequest;
import com.saas.employee.dto.response.DutyStationResponse;
import com.saas.employee.exception.ResourceExistsException;
import com.saas.employee.mapper.DutyStationMapper;
import com.saas.employee.model.DutyStation;
import com.saas.employee.repository.DutyStationRepository;
import com.saas.employee.utility.ValidationUtil;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DutyStationService {

    private final DutyStationRepository dutyStationRepository;
    private final DutyStationMapper dutyStationMapper;
    private final ValidationUtil validationUtil;

    public DutyStationResponse addDutyStation(UUID tenantId,
                                              DutyStationRequest request) {

        if (dutyStationRepository.existsByTenantIdAndName(tenantId, request.getName())) {
            throw new ResourceExistsException("Duty station already exists");
        }
        DutyStation dutyStation = dutyStationMapper.mapToEntity(tenantId, request);
        dutyStation = dutyStationRepository.save(dutyStation);
        return dutyStationMapper.mapToDto(dutyStation);
    }

    public List<DutyStationResponse> getAllDutyStations(UUID tenantId) {

        List<DutyStation> dutyStations = dutyStationRepository.findByTenantId(tenantId);
        return dutyStations.stream().map(dutyStationMapper::mapToDto).toList();
    }

    public DutyStationResponse getDutyStationById(UUID tenantId, UUID stationId) {

        DutyStation dutyStation = validationUtil.getDutyStationById(tenantId, stationId);
        return dutyStationMapper.mapToDto(dutyStation);
    }

    public DutyStationResponse updateDutyStation(UUID tenantId,
                                                 UUID stationId,
                                                 DutyStationRequest request) {

        DutyStation dutyStation = validationUtil.getDutyStationById(tenantId, stationId);
        if (dutyStationRepository.existsByTenantIdAndNameAndIdNot(
                tenantId, request.getName(), dutyStation.getId())) {
            throw new ResourceExistsException("Duty station already exists");
        }
        dutyStation = dutyStationMapper.mapUpdateRequest(dutyStation, request);
        dutyStation = dutyStationRepository.save(dutyStation);
        return dutyStationMapper.mapToDto(dutyStation);
    }

    public void deleteDutyStation(UUID tenantId,
                                  UUID stationId) {

        DutyStation dutyStation = validationUtil.getDutyStationById(tenantId, stationId);
        dutyStationRepository.delete(dutyStation);
    }
}
