package com.micro.pacientesms.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.micro.pacientesms.adapter.PacienteAdapter;
import com.micro.pacientesms.dto.PacienteDTO;
import com.micro.pacientesms.forms.PacienteForm;
import com.micro.pacientesms.model.Paciente;
import com.micro.pacientesms.regras.Regra;
import com.micro.pacientesms.regras.RegrasDePacientes;
import com.micro.pacientesms.repository.PacienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PacienteServices {

    @Autowired
    private PacienteRepository pacienteRepository;
    private EnderecoServices enderecoServices;

    public PacienteDTO cadastraPaciente(PacienteForm pacienteForm) {
        Paciente paciente = adaptaFormularioDePaciente(pacienteForm);
        paciente.setStatus(true);
        System.out.println(paciente);
        if (jaPossuiAlgumCadastroNoSistema(paciente)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Ja possui cadastro deste Paciente");
        }
        enderecoServices.cadastraEndereco(paciente.getEndereco());
        pacienteRepository.save(paciente);
        return new PacienteDTO(paciente);
    }

    private boolean jaPossuiAlgumCadastroNoSistema(Paciente paciente) {
        Optional<Paciente> pacienteExistente = pacienteRepository.findByDadosCadastraisCpf(paciente.getCPF());
        if (pacienteExistente.isPresent()) {
            return true;
        }
        return false;
    }

    public PacienteDTO editaPaciente(Long id, PacienteForm pacienteFormComEdicoes) {
        Paciente pacienteAserEditado = encontraPaciente(id);
        try {
            validaEdicoes(pacienteAserEditado, pacienteFormComEdicoes);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), e.toString());
        }
        Paciente pacienteComEdicoes = adaptaFormularioDePaciente(pacienteFormComEdicoes);
        pacienteAserEditado.setDadosCadastrais(pacienteComEdicoes.getDadosCadastrais());
        pacienteAserEditado.setEndereco(pacienteComEdicoes.getEndereco());
        enderecoServices.cadastraEndereco(pacienteAserEditado.getEndereco());
        pacienteRepository.save(pacienteAserEditado);
        return new PacienteDTO(pacienteAserEditado);

    }

    public PacienteDTO encontraPacientePeloId(Long id) {
        return pacienteRepository.findByIdAndStatus(id, true).map(pacienteEncontrado -> {
            return new PacienteDTO(pacienteEncontrado);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Paciente nao encontrado"));
    }

    public ResponseEntity<Object> apagaPaciente(Long id) {
        Optional<Paciente> pacienteAserExcluido = pacienteRepository.findByIdAndStatus(id, true);
        if (pacienteAserExcluido.isEmpty()) {
            return ResponseEntity.notFound().build();
        } // Pq as pessoas gostam disso abaixo?
        return pacienteAserExcluido.map(pacienteEmExclusao -> {
            pacienteEmExclusao.setStatus(false);
            pacienteRepository.save(pacienteEmExclusao);
            System.out.println(pacienteEmExclusao);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.badRequest().build());
    }

    public void validaEdicoes(Paciente paciente, PacienteForm pacienteForm) throws Exception {
        final Regra regras = new RegrasDePacientes(
                pacienteForm, paciente);
        regras.validar();
    }

    public Paciente adaptaFormularioDePaciente(PacienteForm pacienteForm) {
        final PacienteAdapter pacienteAdapter = new PacienteAdapter(pacienteForm);
        return pacienteAdapter.converteParaPaciente();
    }

    public Page<Paciente> listaPacientes(Pageable pageable) {
        return pacienteRepository.findAllByStatus(true, pageable);
    }

    public Paciente encontraPaciente(Long id) {
        return pacienteRepository.findByIdAndStatus(id, true)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Paciente nao encontrado"));
    }

}
