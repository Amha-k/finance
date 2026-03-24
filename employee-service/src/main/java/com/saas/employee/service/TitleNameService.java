package com.saas.employee.service;

import com.saas.employee.dto.request.TitleNameRequest;
import com.saas.employee.dto.response.TitleNameResponse;
import com.saas.employee.exception.ResourceExistsException;
import com.saas.employee.mapper.TitleNameMapper;
import com.saas.employee.model.TitleName;
import com.saas.employee.repository.TitleNameRepository;
import com.saas.employee.utility.ValidationUtil;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TitleNameService {

    private final TitleNameRepository titleNameRepository;
    private final TitleNameMapper titleNameMapper;
    private final ValidationUtil validationUtil;

    public TitleNameResponse addTitleName(UUID tenantId,
                                          TitleNameRequest request) {

        TitleName titleName = titleNameMapper.mapToEntity(tenantId, request);
        if (titleNameRepository
                .existsByTitleNameAndTenantId(request.getTitleName(), tenantId)) {
            throw new ResourceExistsException(
                    "Title name '" + request.getTitleName() + "' already exists");
        }
        titleName = titleNameRepository.save(titleName);
        return titleNameMapper.mapToDto(titleName);
    }

    public List<TitleNameResponse> getAllTitleNames(UUID tenantId) {

        List<TitleName> titleNames = titleNameRepository.findAll();
        return titleNames.stream()
                .filter(title -> title.getTenantId().equals(tenantId))
                .map(titleNameMapper::mapToDto)
                .toList();
    }

    public TitleNameResponse getTitleNameById(UUID tenantId,
                                              UUID titleId) {

        TitleName titleName = validationUtil.getTitleNameById(tenantId, titleId);
        return titleNameMapper.mapToDto(titleName);
    }

    public TitleNameResponse updateTitleName(UUID tenantId,
                                             UUID titleId,
                                             TitleNameRequest request) {

        TitleName titleName = validationUtil.getTitleNameById(tenantId, titleId);
        if (titleNameRepository.existsByTenantIdAndTitleNameAndIdNot(
                tenantId, request.getTitleName(), titleName.getId())) {
            throw new ResourceExistsException(
                    "Title name '" + request.getTitleName() + "' already exists");
        }
        titleName = titleNameMapper.mapUpdateRequest(titleName, request);
        titleName = titleNameRepository.save(titleName);
        return titleNameMapper.mapToDto(titleName);
    }

    public void deleteTitleName(UUID tenantId,
                                UUID titleId) {

        TitleName titleName = validationUtil.getTitleNameById(tenantId, titleId);
        titleNameRepository.delete(titleName);
    }
}
