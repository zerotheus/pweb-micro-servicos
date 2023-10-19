package com.micro.pacientesms.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.micro.pacientesms.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Optional<Endereco> findByUfAndBairroAndCidadeAndCep(String uf, String cidade, String bairro, String Cep);

}
