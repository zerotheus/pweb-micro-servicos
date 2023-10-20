package com.micro.pacientesms.regras;

import com.micro.pacientesms.forms.PacienteForm;
import com.micro.pacientesms.model.Paciente;

public class RegrasDePacientes implements Regra {

    private PacienteForm pacienteForm;
    private Paciente paciente;

    public RegrasDePacientes(PacienteForm pacienteForm, Paciente paciente) {
        this.pacienteForm = pacienteForm;
        this.paciente = paciente;
    }

    @Override
    public boolean validar() throws Exception {
        return emailNaoFoiAlterado() && cpfNaoFoiAlterado();
    }

    private boolean emailNaoFoiAlterado() throws Exception {
        if (!pacienteForm.getEmail().equals(paciente.getEmail().toString())) {
            throw new Exception("Email nao pode ser alterado");
        }
        return true;
    }

    private boolean cpfNaoFoiAlterado() throws Exception {
        if (!pacienteForm.getCPF().equals(paciente.getCPF().toString())) {
            throw new Exception("cpf nao pode ser alterado");
        }
        return true;
    }

}
