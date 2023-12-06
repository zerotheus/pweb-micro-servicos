package com.micro.consultas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.consultas.dto.ConsultaDto;
import com.micro.consultas.form.CancelamentoForm;
import com.micro.consultas.form.ConsultaForm;
import com.micro.consultas.service.ConsultaService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/Consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<ConsultaDto> agendar(@RequestBody ConsultaForm consulta) {
        System.out.println(consulta);
        return ResponseEntity.ok().body(new ConsultaDto(consultaService.agendaConsulta(consulta)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDto> verConsulta(@PathVariable Long id) {
        return ResponseEntity.ok().body(new ConsultaDto(consultaService.encontraConsulta(id)));
    }

    @GetMapping("/list/{page}")
    public ResponseEntity<Page<ConsultaDto>> listarConsultas(@PathVariable int page){
        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok().body(consultaService.listarConsultas(pageable).map(ConsultaDto::new));
    }

    @DeleteMapping
    public ResponseEntity<ConsultaDto> cancelar(@Valid @RequestBody CancelamentoForm cancelamento) throws Exception {
        return ResponseEntity.ok().body(new ConsultaDto(consultaService.cancelaConsulta(cancelamento)));
    }

}
