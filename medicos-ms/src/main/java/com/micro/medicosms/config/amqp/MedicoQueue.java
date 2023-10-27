package com.micro.medicosms.config.amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MedicoQueue {

    @Bean
    public Queue initQueue() {
        return QueueBuilder.durable("Medicos").build();
    }

    @Bean
    public Queue iniciaFilaDeListagem() {
        return QueueBuilder.durable("Liste todos Medicos").build();
    }

}
