package com.micro.pacientesms.forms;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EnderecoForm {

    private final Long numero;
    private final String uf;
    private final String complemento;
    private final String bairro;
    private final String cidade;
    private final String cep;
    private final String logradouro;

    @JsonCreator
    public EnderecoForm(Long numero, String uf, String complemento, String bairro, String cidade, String cep,
            String logradouro) {
        this.numero = numero;
        this.uf = uf;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
        this.logradouro = logradouro;
    }

}
