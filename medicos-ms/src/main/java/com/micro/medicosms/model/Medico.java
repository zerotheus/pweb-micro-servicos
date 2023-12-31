package com.micro.medicosms.model;

import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.micro.medicosms.model.Campos.DadosCadastrais;
import com.micro.medicosms.model.Campos.Email;
import com.micro.medicosms.model.Campos.Especialidade;
import com.micro.medicosms.model.Campos.Telefone;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "medicos")
@Setter
@Getter
@ToString
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Medico_id")
    private Long id;
    private DadosCadastrais dadosCadastrais;

    @ManyToOne
    private Endereco endereco;

    private Boolean status;
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

    public String getCRM() {
        return this.getDadosCadastrais().getCrm();
    }

    public Especialidade getEspecialidade() {
        return this.getDadosCadastrais().getEspecialidade();
    }

}
