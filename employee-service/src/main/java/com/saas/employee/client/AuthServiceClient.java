package com.saas.employee.client;

import com.saas.employee.dto.clientDto.UserDto;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "auth-service", path = "/api/auth")
public interface AuthServiceClient {

    @GetMapping("/users/{tenantId}/get/username")
    UserDto getUserByUsername(@PathVariable UUID tenantId,
                              @RequestParam String username);
}
