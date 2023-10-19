package com.micro.pacientesms.model.Campos;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
public class Telefone {

    private String telefone;

    @Override
    public String toString() {
        return this.telefone;
    }

}
