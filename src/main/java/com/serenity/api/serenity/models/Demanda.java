package com.serenity.api.serenity.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Demanda extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private Double custoTotal;
    private Integer tipoContrato;

    @ManyToOne
    private Evento evento;

    @ManyToOne
    private Usuario responsavel;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Escala> escalas;

}
