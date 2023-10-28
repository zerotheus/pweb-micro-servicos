package com.micro.consultas.service;

import com.micro.consultas.amqp.MensagemAMQP;
import com.micro.consultas.model.Consulta;
import com.micro.consultas.model.enums.Status;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MedicoResponseHandler implements ResponseHandler {

    private final ConsultaService consultaService;

    @Override
    public void alterarEstado(Consulta consulta, MensagemAMQP message) throws Exception {
        medicoExiste(message);
        medicoTemDisponibilidade(consulta, message);
        consultaEstaEmEstadoValido(consulta);
        estaAguardandoRespostaDoMedico(consulta);
        estaEmAnalise(consulta);
        consulta.setFkMedicoId(message.getRequiredId());
    }

    private void medicoExiste(MensagemAMQP message) throws Exception {
        if (!message.isExiste()) {
            throw new Exception("Medico nao existe nos cadastros");
        }
    }

    private void medicoTemDisponibilidade(Consulta consulta, MensagemAMQP message) {
        consultaService.medicoTemDisponibilidade(consulta, message.getRequiredId());
    }

    private boolean consultaEstaEmEstadoValido(Consulta consulta) {
        if (consulta.getEstado() == Status.Remarcar || consulta.getEstado() == Status.Agendada) {
            return false;
        }
        return true;
    }

    private boolean estaAguardandoRespostaDoMedico(Consulta consulta) {
        if (consulta.getEstado() == Status.AnaliseDeConfirmacaoMedicaPendente) {
            consulta.setEstado(Status.Agendada);
            return true;
        }
        return false;
    }

    private void estaEmAnalise(Consulta consulta) {
        if (consulta.getEstado() == Status.Analise) {
            consulta.setEstado(Status.ValidacaoDeDadosDoPacientePendente);
        }
    }

}
