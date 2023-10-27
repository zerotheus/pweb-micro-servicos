package com.micro.consultas.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.micro.consultas.Regra.RegrasDeMarcacaoDeConsulta;
import com.micro.consultas.adapter.ConsultaAdapter;
import com.micro.consultas.amqp.ListagemMessage;
import com.micro.consultas.amqp.MensagemAMQP;
import com.micro.consultas.form.ConsultaForm;
import com.micro.consultas.model.Consulta;
import com.micro.consultas.model.enums.Status;
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
        Consulta consulta = consultaRepository.save(adaptaConsulta(consultaForm));
        envieMensagens(consulta);
        return consulta;
    }

    public void regrasDeMarcacao(ConsultaForm consultaForm) {
        new RegrasDeMarcacaoDeConsulta(consultaForm).validar();
    }

    public Consulta adaptaConsulta(ConsultaForm consultaForm) {
        return new ConsultaAdapter(consultaForm).converte();
    }

    public void envieMensagens(Consulta consulta) {
        enviaMensagemParaMedico(consulta);
        enviaMensagemParaPaciente(consulta);
    }

    private void enviaMensagemParaMedico(Consulta consulta) {
        if (consulta.getFkMedicoId() != null) {
            mensagemDeMedificoInformado(consulta);
        }
        rabbitTemplate.convertAndSend("Liste todos Medicos", new ListagemMessage());
    }

    private void mensagemDeMedificoInformado(Consulta consulta) {
        MensagemAMQP mensagem = new MensagemAMQP();
        mensagem.setConsultaId(consulta.getId());
        mensagem.setRequiredId(consulta.getFkMedicoId());
        rabbitTemplate.convertAndSend("Medicos", mensagem);
        return;
    }

    private void enviaMensagemParaPaciente(Consulta consulta) {
        MensagemAMQP mensagem = new MensagemAMQP();
        mensagem.setConsultaId(consulta.getId());
        mensagem.setRequiredId(consulta.getFkPacienteId());
        System.out.println(consulta);
        rabbitTemplate.convertAndSend("Pacientes",
                mensagem);
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

    public boolean medicoTemDisponibilidade(Consulta consulta, Long medicoId) {
        if (consultaRepository.medicosIndisponiveisAsMap(consulta.getHorario(), consulta.getHorario().plusHours(1))
                .containsKey(medicoId)) {
            consulta.setEstado(Status.Remarcar);
            return false;
        }
        return true;
    }

    public Consulta encontraConsulta(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "consulta nao encontrada"));
    }

    public Long aleatorizaMedico(Long consultaId, List<Long> medicos) {
        Consulta consulta = this.encontraConsulta(consultaId);
        HashMap<Long, Long> hash = consultaRepository.medicosIndisponiveisAsMap(consulta.getHorario(),
                consulta.getHorario().plusHours(1));
        removeMedicosIndisiponiveis(hash, medicos);
        return defineMedicoDaConsulta(medicos);
    }

    private void removeMedicosIndisiponiveis(HashMap<Long, Long> hash, List<Long> medicos) {
        for (int i = 0; i < medicos.size(); i++) {
            if (hash.containsKey(medicos.get(i)) && hash.size() != 0) {
                medicos.remove(i);
            }
        }
    }

    private Long defineMedicoDaConsulta(List<Long> medicos) {
        Random random = new Random();
        return medicos.get(random.nextInt(0, medicos.size()));
    }

}
