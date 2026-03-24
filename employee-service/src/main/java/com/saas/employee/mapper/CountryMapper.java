package com.saas.employee.mapper;

import com.saas.employee.dto.request.CountryRequest;
import com.saas.employee.dto.response.CountryResponse;
import com.saas.employee.model.Country;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CountryMapper {

    public Country mapToEntity(UUID tenantId,
                               CountryRequest request) {

        Country country = new Country();
        country.setTenantId(tenantId);
        country.setName(request.getName());
        country.setAbbreviatedName(request.getAbbreviatedName());
        country.setCode(request.getCode());

        return country;
    }

    public CountryResponse mapToDto(Country country) {

        CountryResponse response = new CountryResponse();
        response.setId(country.getId());
        response.setName(country.getName());
        response.setAbbreviatedName(country.getAbbreviatedName());
        response.setCode(country.getCode());
        response.setTenantId(country.getTenantId());
        response.setCreatedAt(country.getCreatedAt());
        response.setUpdatedAt(country.getUpdatedAt());
        response.setCreatedBy(country.getCreatedBy());
        response.setUpdatedBy(country.getUpdatedBy());

        return response;
    }

    public Country mapUpdateRequest(Country country,
                                CountryRequest request) {

        if (request.getName() != null) {
            country.setName(request.getName());
        }

        if (request.getAbbreviatedName() != null) {
            country.setAbbreviatedName(request.getAbbreviatedName());
        }

        if (request.getCode() != null) {
            country.setCode(request.getCode());
        }

        return country;
    }
}
