package com.saas.employee.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {

    private final ApplicationProperties properties;

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(properties.employeeEventsExchange());
    }

    @Bean
    public Queue createEmployeeQueue() {
        return QueueBuilder.durable(properties.createEmployeeQueue()).build();
    }

    @Bean
    public Binding createEmployeeBinding() {
        return BindingBuilder.bind(createEmployeeQueue()).to(exchange()).with(properties.createEmployeeQueue());
    }

    @Bean
    public Queue updateEmployeeQueue() {
        return QueueBuilder.durable(properties.updateEmployeeQueue()).build();
    }

    @Bean
    public Binding updateEmployeeBinding() {
        return BindingBuilder.bind(updateEmployeeQueue()).to(exchange()).with(properties.updateEmployeeQueue());
    }

    @Bean
    public Queue deleteEmployeeQueue() {
        return QueueBuilder.durable(properties.deleteEmployeeQueue()).build();
    }

    @Bean
    public Binding deleteEmployeeBinding() {
        return BindingBuilder.bind(deleteEmployeeQueue()).to(exchange()).with(properties.deleteEmployeeQueue());
    }

    @Bean
    public Jackson2JsonMessageConverter jacksonConverter(ObjectMapper mapper) {
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory, ObjectMapper mapper) {
        final var rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(jacksonConverter(mapper));
        return rabbitTemplate;
    }
}
