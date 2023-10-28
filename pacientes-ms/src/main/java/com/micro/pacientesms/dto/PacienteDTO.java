package com.micro.pacientesms.dto;

import com.micro.pacientesms.model.Paciente;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PacienteDTO {

    private Long NumeroDePaciente;
    private String email;
    private String nome;
    private String cpf;

    public PacienteDTO(Paciente paciente) {
        this.NumeroDePaciente = paciente.getId();
        this.email = paciente.getEmail().toString();
        this.nome = paciente.getNome();
        this.cpf = paciente.getCPF().toString();
    }

}
