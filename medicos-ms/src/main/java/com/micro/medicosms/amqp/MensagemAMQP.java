package com.micro.medicosms.amqp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class MensagemAMQP {

    private Long consultaId;
    private Long requiredId;
    private boolean existe = false;

}
