package com.saas.employee.client;

import com.saas.employee.dto.clientDto.ShiftDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "attendance-service", path = "/api/attendance")
public interface AttendanceServiceClient {

    @GetMapping("/shifts/get/{shiftId}")
    ShiftDto getShiftById(@PathVariable UUID shiftId);
}
