package com.micro.consultas.amqp;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ListagemMessage {

    private Integer senderId;
    private Long consultaId;
    private List<Long> lista;

    public ListagemMessage() {
        if (senderId == null) {
            this.senderId = new Random().nextInt(1, Integer.MAX_VALUE);
            this.lista = new LinkedList<Long>();
        }
    }

}
