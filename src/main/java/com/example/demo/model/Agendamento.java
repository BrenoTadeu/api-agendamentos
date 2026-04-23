package com.example.demo.model;
import java.time.LocalDateTime;
import java.time.ZoneId;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "agendamentos")
@Data
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nomeCliente;
    @Column(nullable = false)
    private LocalDateTime dataSelecionada;
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private OpcaoCliente opcaodoCliente;    
    @JsonFormat(pattern = "dd/MM/YYYY HH:mm")
    @Column(nullable = false)
    private LocalDateTime dataHora;
    
    @PrePersist
    public void PrePersist(){
        this.dataHora = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }
}
