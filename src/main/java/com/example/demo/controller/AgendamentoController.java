package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.AgendamentoRequest;
import com.example.demo.DTO.AgendamentoResponse;
import com.example.demo.service.AgendamentoService;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {
    
    @Autowired
    private AgendamentoService service;

    @PostMapping
    public AgendamentoResponse criar(@RequestBody AgendamentoRequest dto){
        return service.criar(dto);
    }

    @GetMapping("/listar")
    public List<AgendamentoResponse> listarAgendamentos(){
        return service.listarAgendamentos();
    }

    @GetMapping("/{id}")
    public AgendamentoResponse listarPorId(@PathVariable Long id){
        return service.listarPorId(id);
    }
}
