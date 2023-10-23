package com.micro.medicosms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.medicosms.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

}
