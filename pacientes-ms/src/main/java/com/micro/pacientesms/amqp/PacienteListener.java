package com.micro.pacientesms.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.micro.pacientesms.Service.PacienteServices;

@Component
public class PacienteListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private PacienteServices pacienteServices;

    @RabbitListener(queues = "Pacientes")
    public void EsperaPedidos(MensagemAMQP message) {
        System.out.println(message);// TODO decidir a forma de tratar erro
        try {
            pacienteServices.encontraPaciente(message.getRequiredId());
            message.setExiste(true);
            rabbitTemplate.convertAndSend("Pacientes Response", message);
        } catch (Exception e) {
            rabbitTemplate.convertAndSend("Pacientes Response", message);
        }
    }

}
