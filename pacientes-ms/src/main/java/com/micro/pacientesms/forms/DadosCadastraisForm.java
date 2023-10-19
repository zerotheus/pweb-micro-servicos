package com.micro.pacientesms.forms;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DadosCadastraisForm {

    private final String email;
    private final String telefone;
    private final String nome;
    private final String cpf;

    @JsonCreator
    public DadosCadastraisForm(String email, String telefone, String nome, String cpf) {
        this.email = email;
        this.telefone = telefone;
        this.nome = nome;
        this.cpf = cpf;
    }

}