package com.serenity.api.serenity.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Escala {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer funcaoEscala;
    private Integer qtdColaborador;
    private Integer qtdHora;
    private Double valor;
    private Boolean comissionado;
    private Boolean asoObrigatorio;

    @ManyToOne
    private Evento evento;
}

