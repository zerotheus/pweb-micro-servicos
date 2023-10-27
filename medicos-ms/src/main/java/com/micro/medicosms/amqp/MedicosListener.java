package com.micro.medicosms.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.micro.medicosms.service.MedicoServices;

@Component
public class MedicosListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MedicoServices medicoServices;

    @RabbitListener(queues = "Medicos")
    public void EsperaPedidos(MensagemAMQP message) {
        System.out.println(message);
        try {
            medicoServices.encontraUmMedico(message.getRequiredId());
            message.setExiste(true);
            rabbitTemplate.convertAndSend("Medicos Response", message);
        } catch (Exception e) {
            rabbitTemplate.convertAndSend("Medicos Response", message);
        }
    }

    @RabbitListener(queues = "Liste todos Medicos")
    public void EsperaSolicitacoesDeListagem(ListagemMessage listagem) {
        listagem.setSenderId(0);
        medicoServices.listaMedicos(listagem.getLista());
    }

}
