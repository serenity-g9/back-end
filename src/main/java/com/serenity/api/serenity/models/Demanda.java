package com.serenity.api.serenity.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Demanda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;
    private LocalDate inicio;
    private LocalDate fim;
    private Double custoTotal;
    private Integer tipoContrato;

    @ManyToOne
    private Evento evento;

    @ManyToOne
    private Usuario responsavel;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Escala> escalas;

}
