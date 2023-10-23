package com.micro.medicosms.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.medicosms.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Optional<Medico> findByDadosCadastraisCrm(String crm);// TODO adicionar status em discussao se devo ou nao

    Optional<Medico> findByIdAndStatus(Long id, Boolean status);

    Page<Medico> findAllByStatus(Boolean status, Pageable pageable);

}
