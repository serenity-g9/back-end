package com.serenity.api.serenity.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@DiscriminatorValue("ESCALA")
public class Escala extends Faturamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int idColaborador;
    private int idEvento;
    private LocalDateTime dataHorario;
    private int funcaoAlocada;
    private Double faturamento;
}
