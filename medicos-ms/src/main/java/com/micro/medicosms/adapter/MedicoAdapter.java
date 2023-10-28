package com.micro.medicosms.adapter;

import com.micro.medicosms.form.MedicoForm;
import com.micro.medicosms.model.Endereco;
import com.micro.medicosms.model.Medico;
import com.micro.medicosms.model.Campos.DadosCadastrais;

public class MedicoAdapter {

    private MedicoForm medicoForm;

    public MedicoAdapter(MedicoForm medicoForm) {
        this.medicoForm = medicoForm;
    }

    public Medico converte() {
        Medico medico = new Medico();
        medico.setDadosCadastrais(converteDadosCadastrais());
        medico.setEndereco(converteEndereco());
        return medico;
    }

    private DadosCadastrais converteDadosCadastrais() {
        DadosCadastraisAdapter dadosCadastraisAdapter = new DadosCadastraisAdapter(medicoForm.getDadosCadastrais());
        return dadosCadastraisAdapter.converte();
    }

    private Endereco converteEndereco() {
        EnderecoAdapter enderecoAdapter = new EnderecoAdapter(medicoForm.getEndereco());
        return enderecoAdapter.converte();
    }

}
