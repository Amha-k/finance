package com.saas.employee.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saas.employee.dto.eventDto.EmployeeEventDto;
import com.saas.employee.enums.EmployeeEventType;
import com.saas.employee.event.EmployeeEventProducer;
import com.saas.employee.model.EmployeeEvent;
import com.saas.employee.repository.EmployeeEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeEventService {

    private final EmployeeEventRepository employeeEventRepository;
    private final EmployeeEventProducer employeeEventProducer;
    private final ObjectMapper objectMapper;

    public void createEmployeeEvent(EmployeeEventDto event) {

        EmployeeEvent employeeEvent = new EmployeeEvent();
        employeeEvent.setEventType(EmployeeEventType.CREATE_EMPLOYEE);
        employeeEvent.setPayload(toJsonPayload(event));
        log.info("Created employee event: {}", event);
        employeeEventRepository.save(employeeEvent);
    }

    public void updateEmployeeEvent(EmployeeEventDto event) {

        EmployeeEvent employeeEvent = new EmployeeEvent();
        employeeEvent.setEventType(EmployeeEventType.UPDATE_EMPLOYEE);
        employeeEvent.setPayload(toJsonPayload(event));
        log.info("Updated employee event: {}", event);
        employeeEventRepository.save(employeeEvent);
    }

    public void deleteEmployeeEvent(EmployeeEventDto event) {

        EmployeeEvent employeeEvent = new EmployeeEvent();
        employeeEvent.setEventType(EmployeeEventType.DELETE_EMPLOYEE);
        employeeEvent.setPayload(toJsonPayload(event));
        log.info("Deleted employee event: {}", event);
        employeeEventRepository.save(employeeEvent);
    }

    public void sendEmployeeEvents() {
        Sort sort = Sort.by("createdAt").ascending();
        List<EmployeeEvent> events = employeeEventRepository.findAll(sort);
        log.info("Found {} employee events to be sent", events.size());
        for (EmployeeEvent event : events) {
            this.sendEvent(event);
            employeeEventRepository.delete(event);
        }
    }

    private void sendEvent(EmployeeEvent employeeEvent) {
        EmployeeEventType eventType = employeeEvent.getEventType();
        EmployeeEventDto event = fromJsonPayload(employeeEvent.getPayload(), EmployeeEventDto.class);
        switch (eventType) {
            case CREATE_EMPLOYEE:
                log.info("Send created employee event: {}", event);
                employeeEventProducer.sendCreateEmployeeEvent(event);
                break;
            case UPDATE_EMPLOYEE:
                log.info("Send updated employee event: {}", event);
                employeeEventProducer.sendUpdateEmployeeEvent(event);
                break;
            case DELETE_EMPLOYEE:
                log.info("Send deleted employee event: {}", event);
                employeeEventProducer.sendDeleteEmployeeEvent(event);
                break;
            default:
                log.warn("Unsupported event type: {}", eventType);
        }
    }

    private String toJsonPayload(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T fromJsonPayload(String json, Class<T> type) {
        try {
            return objectMapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
