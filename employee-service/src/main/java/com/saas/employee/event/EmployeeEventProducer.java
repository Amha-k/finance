package com.saas.employee.event;

import com.saas.employee.config.ApplicationProperties;
import com.saas.employee.dto.eventDto.EmployeeEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeEventProducer {

    private final RabbitTemplate rabbitTemplate;
    private final ApplicationProperties properties;

    public void sendCreateEmployeeEvent(EmployeeEventDto event) {
        this.send(properties.createEmployeeQueue(), event);
    }

    public void sendDeleteEmployeeEvent(EmployeeEventDto event) {
        this.send(properties.deleteEmployeeQueue(), event);
    }

    public void sendUpdateEmployeeEvent(EmployeeEventDto event) {
        this.send(properties.updateEmployeeQueue(), event);
    }

    private void send(String routingKey, Object payload) {
        rabbitTemplate.convertAndSend(properties.employeeEventsExchange(), routingKey, payload);
    }
}
