package com.micro.medicosms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.medicosms.model.Endereco;
import com.micro.medicosms.repository.EnderecoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EnderecoServices {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco cadastraEndereco(Endereco endereco) {
        Optional<Endereco> enderecoProcurado = enderecoJaEstaCadastrado(endereco);
        if (enderecoProcurado.isPresent()) {
            return enderecoProcurado.get();
        }
        return enderecoRepository.save(endereco);
    }

    private Optional<Endereco> enderecoJaEstaCadastrado(Endereco endereco) {
        return enderecoRepository.findByUfAndBairroAndCidadeAndCepAndLogradouro(endereco.getUf(), endereco.getCidade(),
                endereco.getBairro(), endereco.getCep(), endereco.getLogradouro());
    }

}