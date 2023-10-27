package com.micro.consultas.service;

import com.micro.consultas.amqp.MensagemAMQP;
import com.micro.consultas.model.Consulta;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MedicoResponseHandler implements ResponseHandler {

    private final ConsultaService consultaService;

    @Override
    public void alterarEstado(Consulta consulta, MensagemAMQP message) throws Exception {
        
    }

}
