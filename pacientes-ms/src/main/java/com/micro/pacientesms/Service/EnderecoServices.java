package com.micro.pacientesms.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.micro.pacientesms.model.Endereco;

import com.micro.pacientesms.repository.EnderecoRepository;

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
        return enderecoRepository.findByUfAndBairroAndCidadeAndCep(null, null, null, null);
    }// TODO PREENCHER CAMPOS

}
