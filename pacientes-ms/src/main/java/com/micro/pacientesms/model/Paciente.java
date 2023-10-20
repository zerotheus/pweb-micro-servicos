package com.micro.pacientesms.model;

import java.time.LocalTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.micro.pacientesms.model.Campos.CPF;
import com.micro.pacientesms.model.Campos.DadosCadastrais;
import com.micro.pacientesms.model.Campos.Email;
import com.micro.pacientesms.model.Campos.Telefone;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "Pacientes")
@Setter
@Getter
@ToString
public class Paciente {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Paciente_id")
    private Long id;
    @Embedded
    private DadosCadastrais dadosCadastrais;
    private Boolean status;

    @ManyToOne
    private Endereco endereco;

    @CreationTimestamp
    private LocalTime created;
    @UpdateTimestamp
    private LocalTime update;

    public Email getEmail() {
        return this.getDadosCadastrais().getEmail();
    }

    public String getNome() {
        return this.getDadosCadastrais().getNome();
    }

    public Telefone getTelefone() {
        return this.getDadosCadastrais().getTelefone();
    }

    public CPF getCPF() {
        return this.getDadosCadastrais().getCpf();
    }

}
