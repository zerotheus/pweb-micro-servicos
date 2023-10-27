package com.micro.consultas.service;

import com.micro.consultas.amqp.MensagemAMQP;
import com.micro.consultas.model.Consulta;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MedicoResponseHandler implements ResponseHandler {

    private final ConsultaService consultaService;

    @Override
    public void alterarEstado(Consulta consulta, MensagemAMQP message) throws Exception {
        medicoExiste(message);
        medicoTemDisponibilidade(consulta, message);
        consultaEstaEmEstadoValido();
        estaAguardandoRespostaDoMedico();
        estaEmAnalise();
    }

    private void medicoExiste(MensagemAMQP message) throws Exception {
        if(!message.isExiste()){
            throw new Exception("Medico nao existe nos cadastros");
        }
    }

    private void medicoTemDisponibilidade(Consulta consulta, MensagemAMQP message) {
        consultaService.medicoTemDisponibilidade(consulta, message.getRequiredId());
    }

    private void consultaEstaEmEstadoValido() {
    }

    private void estaAguardandoRespostaDoMedico() {
    }

    private void estaEmAnalise() {
    }

}
