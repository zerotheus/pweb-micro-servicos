package com.micro.medicosms.regras;

import com.micro.medicosms.form.MedicoForm;
import com.micro.medicosms.model.Medico;

public class RegrasDeMedicos implements Regra {

    private MedicoForm medicoForm;
    private Medico medico;

    public RegrasDeMedicos(MedicoForm medicoForm, Medico medico) {
        this.medicoForm = medicoForm;
        this.medico = medico;
    }

    @Override
    public boolean validar() throws Exception {
        return emailNaoFoiAlterado() && crmNaoFoiAlterado() && especialidadeNaoFoiAlterada();
    }

    private boolean emailNaoFoiAlterado() throws Exception {
        if (!medicoForm.getEmail().equals(medico.getEmail().toString())) {
            throw new Exception("Email nao pode ser alterado");
        }
        return true;
    }

    private boolean crmNaoFoiAlterado() throws Exception {
        if (!medicoForm.getCrm().equals(medico.getCRM().toString())) {
            throw new Exception("crm nao pode ser alterado");
        }
        return true;
    }

    private boolean especialidadeNaoFoiAlterada() throws Exception {
        if (!medicoForm.getEspecialidade().equals(medico.getEspecialidade())) {
            throw new Exception("especialidade nao pode ser alterada");
        }
        return true;
    }

}
