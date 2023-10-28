package com.micro.pacientesms.adapter;

import com.micro.pacientesms.forms.DadosCadastraisForm;
import com.micro.pacientesms.model.Campos.CPF;
import com.micro.pacientesms.model.Campos.DadosCadastrais;
import com.micro.pacientesms.model.Campos.Email;
import com.micro.pacientesms.model.Campos.Telefone;

public class DadosCadastraisAdapter {

    private DadosCadastraisForm dadosCadastraisForm;

    DadosCadastraisAdapter(DadosCadastraisForm dadosCadastraisForm) {
        this.dadosCadastraisForm = dadosCadastraisForm;
    }

    public DadosCadastrais converte() {
        return new DadosCadastrais(converteEmail(), converteTelefone(), converteNome(), converteCpf());
    }

    private Email converteEmail() {
        return new Email(dadosCadastraisForm.getEmail());
    }

    private Telefone converteTelefone() {
        return new Telefone(dadosCadastraisForm.getTelefone());
    }

    private String converteNome() {
        return dadosCadastraisForm.getNome();
    }

    private CPF converteCpf() {
        return new CPF(dadosCadastraisForm.getCpf());
    }

}
