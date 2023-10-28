package com.micro.consultas.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.micro.consultas.service.ConsultaService;
import com.micro.consultas.service.MedicoResponseHandler;
import com.micro.consultas.service.PacienteResponseHandler;

@Component
public class ConsultaResponseListener {

    @Autowired
    private ConsultaService consultaService;

    @RabbitListener(queues = "Medicos Response")
    public void escutaRespostaDeMedico(MensagemAMQP mensagemAMQP) {
        System.out.println("Medico respondeu");
        consultaService.trataResposta(new MedicoResponseHandler(consultaService), mensagemAMQP);
    }

    @RabbitListener(queues = "Pacientes Response")
    public void escutaRespostaDePaciente(MensagemAMQP mensagemAMQP) {
        System.out.println("Paciente respondeu");
        consultaService.trataResposta(new PacienteResponseHandler(consultaService), mensagemAMQP);
    }

    @RabbitListener(queues = "Liste todos Medicos")
    public void listagemListener(ListagemMessage listagemMessage) {
        System.out.println(listagemMessage);
        if (listagemMessage.getSenderId() != 0) {
            return;
        }
        Long id = consultaService.aleatorizaMedico(listagemMessage.getConsultaId(), listagemMessage.getLista());
        consultaService.trataResposta(new MedicoResponseHandler(consultaService), listagemMessage.converte(id));
    }

}
