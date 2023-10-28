package com.micro.medicosms.dto;

import com.micro.medicosms.model.Medico;
import com.micro.medicosms.model.Campos.Especialidade;

import lombok.Getter;

@Getter
public class MedicoDto {

    private Long NumeroIndetificador;
    private String Nome;
    private String Email;
    private String CRM;
    private Especialidade especialidade;

    public MedicoDto(Medico medico) {
        this.NumeroIndetificador = medico.getId();
        this.Nome = medico.getNome();
        this.Email = medico.getEmail().toString();
        this.CRM = medico.getCRM();
        this.especialidade = medico.getDadosCadastrais().getEspecialidade();

    }

}
