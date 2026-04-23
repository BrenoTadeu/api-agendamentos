package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.AgendamentoRequest;
import com.example.demo.DTO.AgendamentoResponse;
import com.example.demo.DTO.HorariosDisponiveis;
import com.example.demo.model.Agendamento;
import com.example.demo.repository.AgendamentoRepository;

@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository repository;

    // This class
    public AgendamentoResponse toDTO(Agendamento a) {
        AgendamentoResponse dto = new AgendamentoResponse();
        dto.setId(a.getId());
        dto.setNomeCliente(a.getNomeCliente());
        dto.setDataSelecionada(a.getDataSelecionada().toString());
        dto.setOpcaodoCliente(a.getOpcaodoCliente().name());
        return dto;
    }

    public AgendamentoResponse criar(AgendamentoRequest dto) {
        Agendamento agendamento = new Agendamento();
        agendamento.setNomeCliente(dto.getNomeCliente());
        agendamento.setDataSelecionada(dto.getDataSelecionada());
        agendamento.setOpcaodoCliente(dto.getOpcaodoCliente());
        if (repository.existsByDataSelecionada(agendamento.getDataSelecionada())) {
            throw new RuntimeException("Já existe um agendamento para esse horário");
        }

        Agendamento salvo = repository.save(agendamento);
        AgendamentoResponse response = new AgendamentoResponse();
        response.setId(salvo.getId());
        response.setNomeCliente(salvo.getNomeCliente());
        response.setDataSelecionada(salvo.getDataSelecionada().toString());

        return response;
    }

    public List<AgendamentoResponse> listarAgendamentos() {
        return repository.findAll()
                .stream()
                .map(this::toDTO).toList();
    }

public List<HorariosDisponiveis> listarAgendamentoDisponivel(LocalDate date){

    List<HorariosDisponiveis> disponiveis = new ArrayList<>();


    LocalDateTime inicioDia = date.atStartOfDay();
    LocalDateTime fimDia = date.atTime(23,59,59);

    List<Agendamento> agendamentos = repository.findByDataHoraBetween(inicioDia, fimDia);

    Set<LocalTime> horariosOcupados = agendamentos.stream()
        .map(a -> a.getDataHora().toLocalTime())
        .collect(Collectors.toSet());

    LocalTime inicio = LocalTime.of(9, 0);
    LocalTime termino = LocalTime.of(21, 0);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    for(LocalTime hora = inicio; !hora.isAfter(termino); hora = hora.plusMinutes(30)){

        System.out.println(hora);
        System.out.println(horariosOcupados); 

        if(!horariosOcupados.contains(hora)){
            disponiveis.add(new HorariosDisponiveis(hora.format(formatter)));
        }
    }

    return disponiveis;
}

    public AgendamentoResponse listarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id não pode estar vazio");
        } else {
            return repository.findById(id)
                    .map(this::toDTO)
                    .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        }
    }

}
