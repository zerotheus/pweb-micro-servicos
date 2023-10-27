package com.micro.consultas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.consultas.Regra.RegrasDeMarcacaoDeConsulta;
import com.micro.consultas.form.ConsultaForm;
import com.micro.consultas.service.ConsultaService;

@RestController
@RequestMapping("/Consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public ConsultaForm testeDePost(@RequestBody ConsultaForm consulta) {
        System.out.println(consulta);
        System.out.println(consultaService.agendaConsulta(consulta));
        return consulta;
    }

}