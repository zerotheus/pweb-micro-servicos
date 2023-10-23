package com.micro.medicosms.model.Campos;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Telefone {

    private String telefone;

    @Override
    public String toString() {
        return this.telefone;
    }

}
