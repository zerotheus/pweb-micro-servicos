package com.micro.medicosms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.medicosms.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Optional<Endereco> findByUfAndBairroAndCidadeAndCepAndLogradouro(String uf, String cidade, String bairro,
            String Cep,
            String logradouro);

}
