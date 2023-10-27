package com.micro.pacientesms.amqp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class MensagemAMQP {
    private Long messageId;
    private Long pacienteId;
    private boolean existe = false;
}
