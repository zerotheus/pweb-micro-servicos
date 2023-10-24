package com.micro.medicosms.form;

import com.micro.medicosms.model.Campos.Especialidade;

import jakarta.validation.Valid;
import lombok.Getter;

@Getter
public class MedicoForm {

    @Valid
    private DadosCadastraisForm dadosCadastrais;
    @Valid
    private EnderecoForm endereco;

    public String getNome() {
        return this.getDadosCadastrais().getNome();
    }

    public String getCrm() {
        return this.getDadosCadastrais().getCrm();
    }

    public String getEmail() {
        return this.getDadosCadastrais().getEmail();
    }

    public Especialidade getEspecialidade() {
        return this.getDadosCadastrais().getEspecialidade();
    }

    public String getTelefone() {
        return this.getDadosCadastrais().getTelefone();
    }

}
