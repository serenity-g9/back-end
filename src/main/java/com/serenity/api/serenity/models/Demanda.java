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
        return String.format("%s,%s,%s,%s,%s,%s,%s",
                id,  // ID
                nome,  // Nome da Demanda
                orcamento,  // Orçamento
                inicio,  // Data de início
                fim,  // Data de fim
                custoTotal,  // Custo total
                tipoContrato,  // Tipo de contrato
                evento != null ? evento.getId() : ""  // ID do Evento, se existir
        );
    }


}
