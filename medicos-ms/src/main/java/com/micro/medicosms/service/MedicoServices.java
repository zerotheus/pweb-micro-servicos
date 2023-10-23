package com.micro.medicosms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.micro.medicosms.form.MedicoForm;
import com.micro.medicosms.model.Medico;
import com.micro.medicosms.repository.MedicoRepository;

public class MedicoServices {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico cadastraMedico(MedicoForm medico) {
        return null;
    }

    public Medico editaMedico(Long id, MedicoForm medico) {
        return null;
    }

    public Medico encontraUmMedico(Long id) {
        return null;
    }

    public void apagaMedico(Long id) {
    }

    public Page<Medico> listaMedicos(Pageable pageable) {
        return medicoRepository.findAllByStatus(true, pageable);
    }

}
