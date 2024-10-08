package com.serenity.api.serenity.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Colaborador colaborador;

    @ManyToOne
    private Escala escala;

    private LocalDateTime horarioEntrada;
}
