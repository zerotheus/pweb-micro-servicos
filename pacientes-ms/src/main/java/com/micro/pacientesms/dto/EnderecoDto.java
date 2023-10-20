package com.micro.pacientesms.dto;

import com.micro.pacientesms.model.Endereco;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class EnderecoDto {

    private String uf;
    private Long numero;
    private String bairro;
    private String cidade;
    private String cep;
    private String logradouro;

    EnderecoDto(Endereco endereco) {
        this.uf = endereco.getUf();
        this.numero = endereco.getNumero();
        this.bairro = endereco.getBairro();
        this.cidade = endereco.getCidade();
        this.cep = endereco.getCep();
        this.logradouro = endereco.getLogradouro();
    }

}
