package com.serenity.api.serenity.models;

import com.serenity.api.serenity.listeners.EscalaListener;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(EscalaListener.class)
public class Escala extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Integer funcaoEscala;
    private Integer qtdColaborador;
    private Integer horasJornada;
    private Integer numeroColete;
    private Integer numeroPulseira;
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "demanda_id", nullable = false)
    private Demanda demanda;


    @OneToMany(mappedBy = "escala", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Agendamento> agendamentos = new ArrayList<>();
}

