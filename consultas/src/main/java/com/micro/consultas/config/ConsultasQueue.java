package com.micro.consultas.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsultasQueue {

    @Bean
    public Queue initPacientesResponseQueue() {
        return QueueBuilder.durable("Medicos Response").build();
    }

    @Bean
    public Queue initMedicosResponseQueue() {
        return QueueBuilder.durable("Pacientes Response").build();
    }

}
