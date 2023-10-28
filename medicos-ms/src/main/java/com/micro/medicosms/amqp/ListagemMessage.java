package com.micro.medicosms.amqp;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ListagemMessage {

    private int senderId;
    private Long consultaId;
    private List<Long> lista;

}
