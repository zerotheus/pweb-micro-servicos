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
        if (jaPossuiAlgumCadastroNoSistema(paciente)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Ja possui cadastro deste Paciente");
        }
        enderecoServices.cadastraEndereco(paciente.getEndereco());// TODO testar inputs
        pacienteRepository.save(paciente);
        return new PacienteDTO(paciente);// TODO atribuir atributos
    }

    private boolean jaPossuiAlgumCadastroNoSistema(Paciente paciente) {
        Optional<Paciente> pacienteExistente = pacienteRepository.findById(null);// TODO trocar por cpf
        if (pacienteExistente.isPresent()) {
            return true;
        }
        return false;
    }

    public PacienteDTO editaPaciente(Long id, PacienteForm pacienteFormComEdicoes) {
        Paciente pacienteAserEditado = encontraPaciente(id);
        try {
            validaEdicoes(pacienteAserEditado, pacienteFormComEdicoes);// TODO escrever algumas regras novamente
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), e.toString());
        }
        Paciente pacienteComEdicoes = adaptaFormularioDePaciente(pacienteFormComEdicoes);
        pacienteAserEditado.setDadosCadastrais(pacienteComEdicoes.getDadosCadastrais());
        pacienteAserEditado.setEndereco(pacienteComEdicoes.getEndereco());// TODO testar inputs
        pacienteRepository.save(pacienteAserEditado);
        return new PacienteDTO(pacienteAserEditado);

    }

    public ResponseEntity<PacienteDTO> encontraPacientePeloId(Long id) {
        return pacienteRepository.findByIdAndStatus(id, true).map(pacienteEncontrado -> {
            return ResponseEntity.ok().body(new PacienteDTO(pacienteEncontrado));
        }).orElse(ResponseEntity.notFound().build());
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
        // final RegrasEspecificasDePaciente regras = new RegrasPaciente(paciente,
        // pacienteForm);
        // regras.validar();
    }// TODO fazer regras

    public Paciente adaptaFormularioDePaciente(PacienteForm pacienteForm) {
        final PacienteAdapter pacienteAdapter = new PacienteAdapter(pacienteForm);
        return pacienteAdapter.converteParaPaciente();
    }

    public Page<Paciente> listaPacientes(Pageable pageable) {
        return pacienteRepository.findAll(pageable);
    }

    public Paciente encontraPaciente(Long id) {
        return pacienteRepository.findByIdAndStatus(id, true)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Paciente nao encontrado"));
    }

}
