package com.saas.employee.event;

import com.saas.employee.dto.request.EmployeeHistoryRequest;
import com.saas.employee.service.EmployeeHistoryService;
import com.saas.employee.utility.EmployeeHistoryContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PromotionEventConsumer {

    private final EmployeeHistoryService employeeHistoryService;

    @RabbitListener(queues = "${rabbitmq.create-promotion-queue}")
    public void consumeCreatePromotionEvent(EmployeeHistoryRequest event) {
        log.info("Received create promotion event: {}", event);
        try {
            EmployeeHistoryContext.set(event);
            employeeHistoryService.addEmployeeHistory(event);
        } catch (Exception e) {
            log.error("Error processing create promotion event: {}", event, e);
        } finally {
            EmployeeHistoryContext.clear();
        }
    }
}
