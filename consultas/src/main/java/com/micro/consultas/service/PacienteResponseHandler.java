package com.micro.consultas.service;

import com.micro.consultas.amqp.MensagemAMQP;
import com.micro.consultas.model.Consulta;
import com.micro.consultas.model.enums.Status;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PacienteResponseHandler implements ResponseHandler {

    private final ConsultaService consultaService;

    @Override
    public void alterarEstado(Consulta consulta, MensagemAMQP message) throws Exception {
        pacienteExiste(message);
        if (temConsultaNoDia(consulta, message)) {
            System.out.println("tem consulta");
            return;
        }
        if (!consultaEstaEmEstadoValido(consulta)) {
            return;
        }
        if (estaAguardandoRespostadoPaciente(consulta)) {
            return;
        }
        estaEmAnalise(consulta);
    }

    public void pacienteExiste(MensagemAMQP response) throws Exception {
        if (!response.isExiste()) {
            throw new Exception("Paciente nao existe nos cadastros");
        }
    }

    private boolean temConsultaNoDia(Consulta consulta, MensagemAMQP message) {
        return consultaService.temConsultaMarcadaNoDia(consulta, message.getRequiredId());
    }

    private boolean consultaEstaEmEstadoValido(Consulta consulta) {
        if (consulta.getEstado() == Status.Remarcar || consulta.getEstado() == Status.Agendada) {
            return false;
        }
        return true;
    }

    private boolean estaAguardandoRespostadoPaciente(Consulta consulta) {
        if (consulta.getEstado() == Status.ValidacaoDeDadosDoPacientePendente) {
            consulta.setEstado(Status.Agendada);
            return true;
        }
        return false;
    }

    private void estaEmAnalise(Consulta consulta) {
        if (consulta.getEstado() == Status.Analise) {
            consulta.setEstado(Status.AnaliseDeConfirmacaoMedicaPendente);
        }
    }

}
