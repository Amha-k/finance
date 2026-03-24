package com.saas.employee.service;

import com.saas.employee.dto.request.CountryRequest;
import com.saas.employee.dto.response.CountryResponse;
import com.saas.employee.mapper.CountryMapper;
import com.saas.employee.model.Country;
import com.saas.employee.repository.CountryRepository;
import com.saas.employee.utility.ValidationUtil;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;
    private final ValidationUtil validationUtil;

    public CountryResponse addCountry(UUID tenantId,
                                      CountryRequest request) {

        Country country = countryMapper.mapToEntity(tenantId, request);
        country = countryRepository.save(country);
        return countryMapper.mapToDto(country);
    }

    public List<CountryResponse> getAllCountries(UUID tenantId) {

        List<Country> countries = countryRepository.findByTenantId(tenantId);
        return countries.stream().map(countryMapper::mapToDto).toList();
    }

    public CountryResponse getCountryById(UUID tenantId,
                                          UUID countryId) {

        Country country = validationUtil.getCountryById(tenantId, countryId);
        return countryMapper.mapToDto(country);
    }

    public CountryResponse updateCountry(UUID tenantId,
                                         UUID countryId,
                                         CountryRequest request) {

        Country country = validationUtil.getCountryById(tenantId, countryId);
        country = countryMapper.mapUpdateRequest(country, request);
        country = countryRepository.save(country);
        return countryMapper.mapToDto(country);
    }

    public void deleteCountry(UUID tenantId,
                              UUID countryId) {

        Country country = validationUtil.getCountryById(tenantId, countryId);
        countryRepository.delete(country);
    }
}
