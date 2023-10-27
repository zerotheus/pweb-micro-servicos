package com.micro.consultas.adapter;

import com.micro.consultas.form.ConsultaForm;
import com.micro.consultas.model.Consulta;
import com.micro.consultas.model.enums.Status;

public class ConsultaAdapter {

    private ConsultaForm consultaForm;

    public ConsultaAdapter(ConsultaForm consultaForm) {
        this.consultaForm = consultaForm;
    }

    public Consulta converte() {
        Consulta consulta = new Consulta();
        consulta.setEstado(Status.Analise);
        consulta.setFkMedicoId(consultaForm.getMedicoId());
        consulta.setFkPacienteId(consultaForm.getPacienteId());
        consulta.setHorario(consultaForm.getDataHorario());
        return consulta;
    }

}
