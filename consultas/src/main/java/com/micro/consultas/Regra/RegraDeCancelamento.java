package com.micro.consultas.Regra;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.micro.consultas.model.Consulta;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegraDeCancelamento implements Regra {

    private Consulta consulta;

    @Override
    public boolean validar() throws Exception {
        return tem24HorasDeAntecedencia();        
    }

    private boolean tem24HorasDeAntecedencia() {
        if (!LocalDateTime.now().plusDays(1).isBefore(consulta.getHorario())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Cancelamento tem que possuir 24 horas de atencedencia");
        }
        return true;
    }

}
