package com.micro.pacientesms.forms;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PacienteForm {

    @Valid // Necessiario em composicoes
    private final DadosCadastraisForm dadosCadastrais;
    @Valid
    private final EnderecoForm endereco;

    @JsonCreator
    public PacienteForm(@JsonProperty("dadosCadastrais") DadosCadastraisForm dadosCadastrais,
            @JsonProperty("endereco") EnderecoForm endereco) {
        this.dadosCadastrais = dadosCadastrais;
        this.endereco = endereco;
    }

    public String getNome() {
        return this.dadosCadastrais.getNome();
    }

    public String getCPF() {
        return this.dadosCadastrais.getCpf();
    }

    public String getEmail() {
        return this.dadosCadastrais.getEmail();
    }

    public String getTelefone() {
        return this.dadosCadastrais.getTelefone();
    }

}
