package com.saas.employee.service;

import com.saas.employee.dto.request.AddressRequest;
import com.saas.employee.dto.response.AddressResponse;
import com.saas.employee.exception.ResourceNotFoundException;
import com.saas.employee.mapper.AddressMapper;
import com.saas.employee.model.Address;
import com.saas.employee.model.Employee;
import com.saas.employee.repository.AddressRepository;
import com.saas.employee.utility.ValidationUtil;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final ValidationUtil validationUtil;

    public AddressResponse addAddress(UUID tenantId,
                                      UUID employeeId,
                                      AddressRequest request) {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        Address address = addressMapper.mapToEntity(tenantId, employee, request);
        address = addressRepository.save(address);
        return addressMapper.mapToDto(address);
    }

    public List<AddressResponse> getAllAddresses(UUID tenantId,
                                                 UUID employeeId) {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        List<Address> addresses = addressRepository.findByEmployeeId(employee.getId());
        return addresses.stream()
                .filter(add -> add.getTenantId().equals(tenantId))
                .map(addressMapper::mapToDto)
                .toList();
    }

    public List<AddressResponse> getEmployeeAddresses(UUID tenantId,
                                                      String employeeId) {

        Employee employee = validationUtil.getEmployeeByEmployeeId(tenantId, employeeId);
        List<Address> addresses = addressRepository.findByEmployeeId(employee.getId());
        return addresses.stream()
                .filter(add -> add.getTenantId().equals(tenantId))
                .map(addressMapper::mapToDto)
                .toList();
    }

    public AddressResponse getAddressById(UUID tenantId,
                                          UUID employeeId,
                                          UUID addressId) {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        Address address = getAddress(tenantId, employee, addressId);
        return addressMapper.mapToDto(address);
    }

    public AddressResponse updateAddress(UUID tenantId,
                                         UUID employeeId,
                                         UUID addressId,
                                         AddressRequest request) {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        Address address = getAddress(tenantId, employee, addressId);
        address = addressMapper.mapUpdateRequest(tenantId, address, request);
        address = addressRepository.save(address);
        return addressMapper.mapToDto(address);
    }

    public void deleteAddress(UUID tenantId,
                              UUID employeeId,
                              UUID addressId) {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        Address address = getAddress(tenantId, employee, addressId);
        addressRepository.delete(address);
    }

    private Address getAddress(UUID tenantId,
                               Employee employee,
                               UUID addressId) {

        return addressRepository
                .findById(addressId)
                .filter(add -> add.getTenantId().equals(tenantId))
                .filter(add -> add.getEmployee().equals(employee))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Address not found with id '" + addressId + "'"));
    }
}
