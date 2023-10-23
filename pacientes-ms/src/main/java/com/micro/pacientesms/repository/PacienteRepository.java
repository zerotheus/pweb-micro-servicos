package com.micro.pacientesms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.micro.pacientesms.model.Paciente;
import com.micro.pacientesms.model.Campos.CPF;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Optional<Paciente> findByDadosCadastraisCpf(CPF cpf);// TODO adicionar status em discussao se devo ou nao

    Optional<Paciente> findByIdAndStatus(Long id, Boolean status);

    List<Paciente> findAllByStatus(Boolean status, Pageable pageable);
}
