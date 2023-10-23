package com.micro.medicosms.form;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.micro.medicosms.model.Campos.Especialidade;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class DadosCadastraisForm {

    @Email(message = "Email invalido", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotBlank(message = "Email requerido")
    private final String email;
    @NotBlank(message = "Telefone requerido")
    @Pattern(message = "Telefone tem somente numeros", regexp = "^[0-9]+$")
    private final String telefone;
    @NotBlank(message = "Nome requerido")
    @Size(min = 3)
    private final String nome;
    @NotNull(message = "CRM requirido")
    private final String crm;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Especialidade especialidade;

    @JsonCreator
    public DadosCadastraisForm(String email, String telefone, String nome, String crm, Especialidade especialidade) {
        this.email = email;
        this.telefone = telefone;
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
    }

}
