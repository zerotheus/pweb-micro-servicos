package com.micro.consultas.dto;

import java.time.LocalDateTime;

import com.micro.consultas.model.Consulta;
import com.micro.consultas.model.enums.Status;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ConsultaDto {

    private final LocalDateTime horario;
    private final Long numeroDaSolicitacao;
    private final Status estado;
    private final String medico;
    private final String paciente;
    private String descricao;

    public ConsultaDto(Consulta consulta) {
        this.horario = consulta.getHorario();
        if (consulta.getFkMedicoId() == null)
            this.medico = "Estamos verificando medicos disponiveis";
        else
            this.medico = consulta.getFkMedicoId().toString();
        this.paciente = consulta.getFkPacienteId().toString();
        this.estado = consulta.getEstado();
        this.numeroDaSolicitacao = consulta.getId();
        switch (consulta.getEstado()) {
            case Analise:
                descricao = "Em analise, por favor aguarde estamos confirmando disponibilidade no horario informado";
                break;
            case Remarcar:
                descricao = "Medico, indisponivel no horario, por favor tente em outro horario ou com outro medico";
                break;
            case Agendada:
                descricao = "Agendada, por favor comparecer no horario e data informados";
                break;
            case ValidacaoDeDadosDoPacientePendente:
                descricao = "Aguarde, enquanto validamos seus dados";
            case AnaliseDeConfirmacaoMedicaPendente:
                descricao = "Aguarde, enquanto avaliamos a disponibilidade do medico";
                break;
            case Cancelada:
                descricao = "Cancelada, Motivo: " + consulta.getMotivoDoCancelamento();
                break;
        }
    }
}
