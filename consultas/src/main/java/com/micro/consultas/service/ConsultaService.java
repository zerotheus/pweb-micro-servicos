package com.micro.consultas.service;

import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.consultas.Regra.RegrasDeMarcacaoDeConsulta;
import com.micro.consultas.adapter.ConsultaAdapter;
import com.micro.consultas.amqp.MensagemAMQP;
import com.micro.consultas.form.ConsultaForm;
import com.micro.consultas.model.Consulta;
import com.micro.consultas.repository.ConsultaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private final MutexConsultas mutexConsultas;

    public Consulta agendaConsulta(ConsultaForm consultaForm) {
        regrasDeMarcacao(consultaForm);
        envieMensagens(consultaForm);
        return consultaRepository.save(adaptaConsulta(consultaForm));
    }

    public void regrasDeMarcacao(ConsultaForm consultaForm) {
        new RegrasDeMarcacaoDeConsulta(consultaForm).validar();
    }

    public Consulta adaptaConsulta(ConsultaForm consultaForm) {
        return new ConsultaAdapter(consultaForm).converte();
    }

    public void envieMensagens(ConsultaForm consultaForm) {
        enviaMensagemParaMedico(consultaForm);
        enviaMensagemParaPaciente(consultaForm);
    }

    private void enviaMensagemParaMedico(ConsultaForm consultaForm) {

    }

    private void enviaMensagemParaPaciente(ConsultaForm consultaForm) {
        rabbitTemplate.convertAndSend("Pacientes", consultaForm.getPacienteId());
    }

    public void trataResposta(ResponseHandler responseHandler, MensagemAMQP message) {
        mutexConsultas.bloqueia(message.getConsultaId());
        Optional<Consulta> consulta = consultaRepository.findById(message.getConsultaId());
        if (consulta.isEmpty()) {
            return;
        }
        try {
            responseHandler.alterarEstado(consulta.get(), message);
            consultaRepository.save(consulta.get());
        } catch (Exception e) {
            consultaRepository.deleteById(message.getConsultaId());
        } finally {
            mutexConsultas.desbloqueia(message.getConsultaId());
        }
    }

}
