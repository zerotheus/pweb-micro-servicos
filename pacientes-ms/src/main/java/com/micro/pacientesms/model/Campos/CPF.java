package com.micro.pacientesms.model.Campos;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CPF {

    private String cpf;

    @Override
    public String toString() {
        return this.getCpf();
    }

}
