package com.saas.employee.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rabbitmq")
public record ApplicationProperties(
        String employeeEventsExchange,
        String createEmployeeQueue,
        String updateEmployeeQueue,
        String deleteEmployeeQueue) {}
