package com.micro.consultas.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.consultas.Regra.RegrasDeMarcacaoDeConsulta;
import com.micro.consultas.adapter.ConsultaAdapter;
import com.micro.consultas.form.ConsultaForm;
import com.micro.consultas.model.Consulta;
import com.micro.consultas.repository.ConsultaRepository;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Consulta agendaConsulta(ConsultaForm consultaForm) {
        regrasDeMarcacao(consultaForm);
        enviaMensagemParaPaciente(consultaForm);
        return adaptaConsulta(consultaForm);
    }

    public void regrasDeMarcacao(ConsultaForm consultaForm) {
        new RegrasDeMarcacaoDeConsulta(consultaForm).validar();
    }

    public Consulta adaptaConsulta(ConsultaForm consultaForm) {
        return new ConsultaAdapter(consultaForm).converte();
    }

    public void enviaMensagemParaMedico() {

    }

    public void enviaMensagemParaPaciente(ConsultaForm consultaForm) {
        rabbitTemplate.convertAndSend("Pacientes", consultaForm.getPacienteId());
    }

}
