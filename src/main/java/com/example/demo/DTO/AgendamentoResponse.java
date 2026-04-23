package com.example.demo.DTO;
import lombok.Data;

@Data
public class AgendamentoResponse {
    private Long id;
    private String nomeCliente;
    private String dataSelecionada;
    private String opcaodoCliente;
}
