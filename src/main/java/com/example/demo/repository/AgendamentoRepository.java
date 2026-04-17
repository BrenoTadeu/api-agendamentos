package com.example.demo.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    boolean existsByDataHora(LocalDateTime dataHora);
}
