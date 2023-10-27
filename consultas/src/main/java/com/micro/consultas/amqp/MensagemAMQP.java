package com.micro.consultas.amqp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MensagemAMQP {
    private Long consultaId;
    private Long requiredId;
    private boolean existe = false;
}
