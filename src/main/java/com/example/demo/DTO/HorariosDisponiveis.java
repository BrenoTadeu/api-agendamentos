package com.example.demo.DTO;

import lombok.Data;

@Data
public class HorariosDisponiveis {
    private String horario;

    public HorariosDisponiveis(String horario){
        this.horario = horario;
    }

}
