package com.micro.medicosms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.medicosms.dto.MedicoDto;
import com.micro.medicosms.form.MedicoForm;
import com.micro.medicosms.service.MedicoServices;

@RestController
@RequestMapping("/Medico")
public class MedicosController {

    @Autowired
    private MedicoServices medicoServices;

    @PostMapping("/Register")
    public ResponseEntity<MedicoDto> cadastraMedico(@RequestBody MedicoForm medico) {
        return ResponseEntity.created(null).body(new MedicoDto(medicoServices.cadastraMedico(medico)));
    }

    @PutMapping("/Edit/{id}")
    public ResponseEntity<MedicoDto> editaMedico(@RequestBody MedicoForm medico, @PathVariable Long id) {
        System.out.println(medico);
        return ResponseEntity.created(null).body(new MedicoDto(medicoServices.editaMedico(id, medico)));
    }

    @GetMapping("{id}")
    public ResponseEntity<MedicoDto> listMedico(@PathVariable Long id) {
        return ResponseEntity.ok().body(new MedicoDto(medicoServices.encontraUmMedico(id)));
    }

    @DeleteMapping("Delete/{id}")
    public ResponseEntity<Object> apagaMedico(@PathVariable Long id) {
        medicoServices.apagaMedico(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list/{page}")
    public ResponseEntity<Page<MedicoDto>> listaPacientes(@PathVariable int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC, "dadosCadastrais.nome"));
        return ResponseEntity.ok().body(medicoServices.listaMedicos(pageable).map(MedicoDto::new));
    }

}
