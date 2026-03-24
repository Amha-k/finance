package com.saas.employee.event;

import com.saas.employee.dto.request.EmployeeHistoryRequest;
import com.saas.employee.service.EmployeeHistoryService;
import com.saas.employee.utility.EmployeeHistoryContext;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransferEventConsumer {

    private final EmployeeHistoryService employeeHistoryService;

    @RabbitListener(queues = "${rabbitmq.create-transfer-queue}")
    public void consumeCreateTransferEvent(@Valid EmployeeHistoryRequest event) {
        log.info("Received create transfer event: {}", event);
        try {
            EmployeeHistoryContext.set(event);
            employeeHistoryService.addEmployeeHistory(event);
        } catch (Exception e) {
            log.error("Error processing create transfer event: {}", event, e);
        } finally {
            EmployeeHistoryContext.clear();
        }
    }
}
