package com.micro.medicosms.adapter;

import com.micro.medicosms.form.DadosCadastraisForm;
import com.micro.medicosms.model.Campos.DadosCadastrais;
import com.micro.medicosms.model.Campos.Email;
import com.micro.medicosms.model.Campos.Telefone;

public class DadosCadastraisAdapter {

    private DadosCadastraisForm dadosCadastraisForm;

    DadosCadastraisAdapter(DadosCadastraisForm dadosCadastraisForm) {
        this.dadosCadastraisForm = dadosCadastraisForm;
    }

    public DadosCadastrais converte() {
        DadosCadastrais dadosCadastrais = new DadosCadastrais();
        dadosCadastrais.setNome(dadosCadastraisForm.getNome());
        dadosCadastrais.setCrm(dadosCadastraisForm.getCrm());
        dadosCadastrais.setEmail(new Email(dadosCadastraisForm.getEmail()));
        dadosCadastrais.setEspecialidade(dadosCadastraisForm.getEspecialidade());
        dadosCadastrais.setTelefone(new Telefone(dadosCadastraisForm.getTelefone()));
        return dadosCadastrais;
    }

}