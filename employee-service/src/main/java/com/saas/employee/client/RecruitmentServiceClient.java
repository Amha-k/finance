package com.saas.employee.client;

import com.saas.employee.dto.clientDto.RecruitmentDto;
import java.util.List;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "recruitment-service", path = "/api/recruitment")
public interface RecruitmentServiceClient {

    @GetMapping("/recruitments/{tenant-id}/get/vacancy")
    RecruitmentDto getRecruitmentByVacancyNumber(@PathVariable("tenant-id") UUID tenantId,
                                                 @RequestParam("vacancy-number") String vacancyNumber);

    @GetMapping("/recruitments/{tenant-id}/get/mode")
    List<RecruitmentDto> getRecruitmentsByMode(@PathVariable("tenant-id") UUID tenantId,
                                               @RequestParam("recruitment-mode") String recruitmentMode);
}
