package com.micro.medicosms.model.Campos;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@ToString
public class DadosCadastrais {
    @Embedded
    private Email email;
    @Embedded
    private Telefone telefone;
    private String nome;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

}
