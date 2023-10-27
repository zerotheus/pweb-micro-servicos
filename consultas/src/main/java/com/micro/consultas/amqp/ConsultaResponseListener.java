package com.micro.consultas.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.micro.consultas.service.ConsultaService;

@Component
public class ConsultaResponseListener {

    @Autowired
    private ConsultaService consultaService;

    @RabbitListener(queues = "Medicos Response")
    public void escutaRespostaDeMedico(MensagemAMQP mensagemAMQP) {
        System.out.println("Medico respondeu");
    }

    @RabbitListener(queues = "Pacientes Response")
    public void escutaRespostaDePaciente(MensagemAMQP mensagemAMQP) {
        System.out.println("Paciente respondeu");
        System.out.println(mensagemAMQP.isExiste());
    }

}
