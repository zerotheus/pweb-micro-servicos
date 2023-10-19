package com.micro.pacientesms.adapter;

import com.micro.pacientesms.forms.PacienteForm;
import com.micro.pacientesms.model.Endereco;
import com.micro.pacientesms.model.Paciente;
import com.micro.pacientesms.model.Campos.DadosCadastrais;

public class PacienteAdapter {

    private PacienteForm pacienteForm;

    public PacienteAdapter(PacienteForm pacienteForm) {
        this.pacienteForm = pacienteForm;
    }

    public Paciente converteParaPaciente() {
        Paciente paciente = new Paciente();
        paciente.setDadosCadastrais(converteDadosCadastrais());
        paciente.setEndereco(converteEndereco());
        return paciente;
    }

    private DadosCadastrais converteDadosCadastrais() {
        DadosCadastraisAdapter dadosAdapter = new DadosCadastraisAdapter(pacienteForm.getDadosCadastrais());
        return dadosAdapter.converte();
    }

    private Endereco converteEndereco() {
        EnderecoAdapter enderecoAdapter = new EnderecoAdapter(pacienteForm.getEndereco());
        return enderecoAdapter.converte();
    }

}
