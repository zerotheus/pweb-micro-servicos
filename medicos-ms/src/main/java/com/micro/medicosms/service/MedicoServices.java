package com.micro.medicosms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.micro.medicosms.adapter.MedicoAdapter;
import com.micro.medicosms.form.MedicoForm;
import com.micro.medicosms.model.Medico;
import com.micro.medicosms.regras.Regra;
import com.micro.medicosms.regras.RegrasDeMedicos;
import com.micro.medicosms.repository.MedicoRepository;

@Service
public class MedicoServices {

    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private EnderecoServices enderecoServices;

    public Medico cadastraMedico(MedicoForm medicoForm) {
        Medico medico = adaptaFormularioDeMedico(medicoForm);
        if (jaPossuiCadastro(medico)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Esta Pessoa ja esta cadastrada");
        }
        medico.setStatus(true);
        enderecoServices.cadastraEndereco(medico.getEndereco());
        return medicoRepository.save(medico);
    }

    private Medico adaptaFormularioDeMedico(MedicoForm medicoForm) {
        MedicoAdapter medicoAdapter = new MedicoAdapter(medicoForm);
        return medicoAdapter.converte();
    }

    public Medico editaMedico(Long id, MedicoForm medicoFormComEdicoes) {
        Medico medicoAserEditado = encontraUmMedico(id);
        try {
            validaEdicoes(medicoAserEditado, medicoFormComEdicoes);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), e.toString());
        }
        Medico medicoComEdicoes = adaptaFormularioDeMedico(medicoFormComEdicoes);
        medicoAserEditado.setDadosCadastrais(medicoComEdicoes.getDadosCadastrais());
        medicoAserEditado.setEndereco(medicoComEdicoes.getEndereco());
        enderecoServices.cadastraEndereco(medicoAserEditado.getEndereco());
        return medicoRepository.save(medicoAserEditado);
    }

    private void validaEdicoes(Medico medicoAserEditado, MedicoForm medicoFormComEdicoes) throws Exception {
        Regra regraDeEdicao = new RegrasDeMedicos(medicoFormComEdicoes, medicoAserEditado);
        regraDeEdicao.validar();
    }

    public Medico encontraUmMedico(Long id) {
        return medicoRepository.findByIdAndStatus(id, true)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Medico nao encontrado"));
    }

    public void apagaMedico(Long id) {
        Medico medico = encontraUmMedico(id);
        medico.setStatus(false);
        medicoRepository.save(medico);
    }

    public Page<Medico> listaMedicos(Pageable pageable) {
        return medicoRepository.findAllByStatus(true, pageable);
    }

    public void listaMedicos(List<Long> list) {
        medicoRepository.findAllByStatus(true).stream().toList().forEach(m -> list.add(m.getId()));
    }

    public boolean jaPossuiCadastro(Medico medico) {
        Optional<Medico> medicoEncontrado = medicoRepository.findByDadosCadastraisCrm(medico.getCRM());
        if (medicoEncontrado.isPresent()) {
            return true;
        }
        return false;
    }

}
