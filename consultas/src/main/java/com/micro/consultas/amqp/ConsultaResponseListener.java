package com.micro.consultas.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsultaResponseListener {

    @RabbitListener(queues = "Medicos Response")
    public void escutaRespostaDeMedico(boolean existe) {
        System.out.println("Medico respondeu");
    }

    @RabbitListener(queues = "Pacientes Response")
    public void escutaRespostaDePaciente(boolean existe) {
        System.out.println("Paciente respondeu");
        System.out.println(existe);
    }

}
