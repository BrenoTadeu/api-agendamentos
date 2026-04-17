package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Agendamento;
import com.example.demo.repository.AgendamentoRepository;

@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository repository;

    public Agendamento criar(Agendamento agendamentos){

        if(agendamentos.getDataHora() == null){
            throw new RuntimeException("Data e hora são obrigatórios");
        }

        if(repository.existsByDataHora(agendamentos.getDataHora())){
            throw new RuntimeException("Já existe um agendamento para esse horário");
        }

        return repository.save(agendamentos);
    }
}
