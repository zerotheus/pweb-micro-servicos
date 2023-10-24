package com.micro.medicosms.config.amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MedicoQueue {
    public class PacienteQueue {

        @Bean
        public Queue initQueue() {
            return QueueBuilder.durable("Pacientes").build();
        }

    }
}
