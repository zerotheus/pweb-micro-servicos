package com.micro.pacientesms.config.amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PacienteQueue {

    @Bean
    public Queue initQueue() {
        // QueueBuilder q = new QueueBuilder().durable();
        return QueueBuilder.durable("Pacientes").build();
    }

}