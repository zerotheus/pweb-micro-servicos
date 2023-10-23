package com.micro.pacientesms.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "Pacientes")
    public void EsperaPedidos(Long id) {
        System.out.println("id: " + id);
    }

}
