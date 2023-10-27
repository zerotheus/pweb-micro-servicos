package com.micro.consultas.service;

import com.micro.consultas.amqp.MensagemAMQP;
import com.micro.consultas.model.Consulta;

public interface ResponseHandler {

    public void alterarEstado(Consulta consulta, MensagemAMQP message) throws Exception;

}
