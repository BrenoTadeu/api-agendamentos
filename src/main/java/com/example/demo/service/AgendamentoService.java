package com.example.demo.service;

/*import java.text.spi.DateFormatProvider;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;*/
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.AgendamentoRequest;
import com.example.demo.DTO.AgendamentoResponse;
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

  /*   public List<AgendamentoResponse> listarAgendamentoMarcado(LocalDate date){
        List<LocalDatetime> horarios = new ArrayList<>();
        LocalDateTime inicio = LocalDateTime.of(date, 9)
        LocalDateTime termino = LocalDateTime.of(21,0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        for(LocalTime hora = inicio; !hora.isAfter(termino); hora = hora.plusMinutes(30)){
            horarios.add(hora.format(formatter));
        }

        String[] arrayHorarios = horarios.toArray(new String[0]);
        Set<LocalDateTime> horariosOcupados = repository.find

        return
    }*/

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
