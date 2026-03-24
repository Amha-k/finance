package com.saas.employee.controller;

import com.saas.employee.dto.request.EmployeeHistoryRequest;
import com.saas.employee.dto.response.EmployeeHistoryResponse;
import com.saas.employee.service.EmployeeHistoryService;
import com.saas.employee.utility.PermissionEvaluator;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/employee/employee-histories/{tenantId}/{employeeId}")
@RequiredArgsConstructor
@Tag(name = "Employee History")
public class EmployeeHistoryController {

    private final EmployeeHistoryService employeeHistoryService;
    private final PermissionEvaluator permissionEvaluator;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllEmployeeHistories(
            @PathVariable UUID tenantId,
            @PathVariable UUID employeeId){

        permissionEvaluator.getEmployeesHistoriesPermission(tenantId, employeeId);

        List<EmployeeHistoryResponse> responses = employeeHistoryService
                .getAllEmployeeHistories(tenantId, employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @GetMapping("/get/{historyId}")
    public ResponseEntity<?> getEmployeeHistoryById(
            @PathVariable UUID tenantId,
            @PathVariable UUID historyId,
            @PathVariable UUID employeeId) {

        permissionEvaluator.getEmployeesHistoriesPermission(tenantId, employeeId);

        EmployeeHistoryResponse response = employeeHistoryService
                .getEmployeeHistoryById(tenantId, historyId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
