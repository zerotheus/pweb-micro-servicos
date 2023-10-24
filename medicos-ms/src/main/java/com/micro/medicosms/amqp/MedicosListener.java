package com.micro.medicosms.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicosListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "Medicos")
    public void EsperaPedidos(Long id) {
        System.out.println("id: " + id);// TODO decidir a forma de tratar erro
    }

}
