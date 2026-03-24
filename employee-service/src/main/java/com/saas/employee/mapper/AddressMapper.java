package com.saas.employee.mapper;

import com.saas.employee.dto.clientDto.LocationDto;
import com.saas.employee.dto.request.AddressRequest;
import com.saas.employee.dto.response.AddressResponse;
import com.saas.employee.model.Address;
import com.saas.employee.model.Employee;
import com.saas.employee.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AddressMapper {

    private final ValidationUtil validationUtil;

    public Address mapToEntity(UUID tenantId,
                               Employee employee,
                               AddressRequest request) {

        LocationDto location = validationUtil.getLocationById(tenantId, request.getLocationId());

        Address address = new Address();
        address.setTenantId(tenantId);
        address.setEmployee(employee);
        address.setLocationId(location.getId());
        address.setAddressType(request.getAddressType());
        address.setHouseNumber(request.getHouseNumber());
        address.setHomeTelephone(request.getHomeTelephone());
        address.setOfficeTelephone(request.getOfficeTelephone());
        address.setMobileNumber(request.getMobileNumber());
        address.setEmail(request.getEmail());
        address.setPoBox(request.getPoBox());

        return address;
    }

    public AddressResponse mapToDto(Address address) {

        AddressResponse response = new AddressResponse();
        response.setId(address.getId());
        response.setTenantId(address.getTenantId());
        response.setAddressType(address.getAddressType().name());
        response.setLocationId(address.getLocationId());
        response.setHouseNumber(address.getHouseNumber());
        response.setHomeTelephone(address.getHomeTelephone());
        response.setOfficeTelephone(address.getOfficeTelephone());
        response.setMobileNumber(address.getMobileNumber());
        response.setEmail(address.getEmail());
        response.setPoBox(address.getPoBox());
        response.setCreatedAt(address.getCreatedAt());
        response.setUpdatedAt(address.getUpdatedAt());
        response.setCreatedBy(address.getCreatedBy());
        response.setUpdatedBy(address.getUpdatedBy());
        response.setEmployeeId(address.getEmployee().getId());

        return response;
    }

    public Address mapUpdateRequest(UUID tenantId,
                                    Address address,
                                    AddressRequest request) {

        LocationDto location = validationUtil.getLocationById(tenantId, request.getLocationId());

        if (request.getLocationId() != null) {
            address.setLocationId(location.getId());
        }
        if (request.getAddressType() != null) {
            address.setAddressType(request.getAddressType());
        }
        if (request.getHouseNumber() != null) {
            address.setHouseNumber(request.getHouseNumber());
        }
        if (request.getHomeTelephone() != null) {
            address.setHomeTelephone(request.getHomeTelephone());
        }
        if (request.getOfficeTelephone() != null) {
            address.setOfficeTelephone(request.getOfficeTelephone());
        }
        if (request.getMobileNumber() != null) {
            address.setMobileNumber(request.getMobileNumber());
        }
        if (request.getEmail() != null) {
            address.setEmail(request.getEmail());
        }
        if (request.getPoBox() != null) {
            address.setPoBox(request.getPoBox());
        }

        return address;
    }
}
