package com.micro.medicosms.amqp;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListagemMessage {

    private int senderId;
    private Long messageId;
    private List<Long> lista;

}
