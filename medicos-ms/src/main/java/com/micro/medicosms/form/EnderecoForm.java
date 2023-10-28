package com.micro.medicosms.form;

import com.fasterxml.jackson.annotation.JsonCreator;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class EnderecoForm {

    @NotNull(message = "numero necessario")
    private final Long numero;
    @NotNull
    @NotBlank(message = "UF requerida")
    private final String uf;
    private final String complemento;
    @NotNull
    @NotBlank(message = "Bairro requerido")
    private final String bairro;
    @NotNull
    @NotBlank(message = "Cidade requerida")
    private final String cidade;
    @NotNull
    @NotBlank(message = "CEP requerido")
    @Size(min = 8, max = 8, message = "Cep precisa de 8 digitos")
    private final String cep;
    @NotNull
    @NotBlank(message = "Logradouro requerido")
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
