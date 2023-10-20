package com.micro.pacientesms.forms;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DadosCadastraisForm {

    @Email(message = "Email invalido", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotBlank(message = "Email requerido")
    private final String email;
    @NotBlank(message = "Telefone requerido")
    @Pattern(message = "Telefone tem somente numeros", regexp = "^[0-9]+$")
    private final String telefone;
    @NotBlank(message = "Nome requerido")
    @Size(min = 3)
    private final String nome;
    // @CPF TODO descomentar validacao muito chato ficar pegando cpf valido
    private final String cpf;

    @JsonCreator
    public DadosCadastraisForm(String email, String telefone, String nome, String cpf) {
        this.email = email;
        this.telefone = telefone;
        this.nome = nome;
        this.cpf = cpf;
    }

}