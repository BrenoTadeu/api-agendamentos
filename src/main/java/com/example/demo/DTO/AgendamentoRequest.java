package com.example.demo.DTO;

import java.time.LocalDateTime;

import com.example.demo.model.OpcaoCliente;

import lombok.Data;

@Data
public class AgendamentoRequest {
    private String nomeCliente;
    private LocalDateTime dataSelecionada;
    private OpcaoCliente opcaodoCliente;
}
