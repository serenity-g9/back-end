package com.serenity.api.serenity.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    private Double orcamento;
    private LocalDate inicio;
    private LocalDate fim;
    private Double custoTotal;
    private Integer tipoContrato;

    @ManyToOne
    private Evento evento;

    @Override
    public String toString() {
        return String.format("%s;%s;%s;%s;%s;%s;%s\n",
                id,
                nome,
                orcamento,
                inicio,
                fim,
                custoTotal,
                tipoContrato,
                evento != null ? evento.getId() : ""
        );
    }


}
