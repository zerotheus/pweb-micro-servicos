package com.micro.consultas.form;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelamentoForm {
    @NotNull(message = "campo obrigatorio")
    private Long consultaId;
    @NotNull(message = "campo obrigatorio")
    private MotivoDoCancelamento motivo;
}
