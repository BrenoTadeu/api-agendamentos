package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    boolean existsByDataSelecionada(LocalDateTime dataSelecionada);
    List<Agendamento> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);
}
