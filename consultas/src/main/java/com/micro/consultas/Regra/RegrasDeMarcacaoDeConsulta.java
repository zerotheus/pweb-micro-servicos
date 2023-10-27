package com.micro.consultas.Regra;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;
import com.micro.consultas.form.ConsultaForm;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegrasDeMarcacaoDeConsulta implements Regra {

    private ConsultaForm consultaForm;

    @Override
    public boolean validar() {
        return tempoDeAtecedenciaEstaDentroDoPrazo(consultaForm.getDataHorario())
                && dentroDosDiasDeFuncionamento(consultaForm.getDataHorario())
                && dentroDosHorariosDeFuncionamento(consultaForm.getDataHorario())
                && temUmaHoraDisponivel(consultaForm.getDataHorario());
    }

    private boolean tempoDeAtecedenciaEstaDentroDoPrazo(LocalDateTime horaMarcada) {
        if (horaMarcada.compareTo(LocalDateTime.now().plusMinutes(30)) <= 0) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Requerimos 30 minutos de atecedencia");
        }
        return true;
    }

    private boolean dentroDosDiasDeFuncionamento(LocalDateTime horaMarcada) {
        if (horaMarcada.getDayOfWeek() == DayOfWeek.SUNDAY) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Funcionamos de Segunda a Sabado");
        }
        return true;
    }

    private boolean dentroDosHorariosDeFuncionamento(LocalDateTime horaMarcada) {
        if (horaMarcada.isBefore(horaMarcada.withHour(7).withMinute(0).withSecond(0))
                || horaMarcada.isAfter(horaMarcada.withHour(19).withMinute(0).withSecond(0))) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400),
                    "Funcionamos de Segunda a Sabado das 7 as 19");
        }
        return true;
    }

    private boolean temUmaHoraDisponivel(LocalDateTime horaMarcada) {
        if (horaMarcada.plusHours(1).isAfter(horaMarcada.withHour(19).withMinute(00))) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "O prazo estimado excede as 19 horas");
        }
        return true;
    }

}
