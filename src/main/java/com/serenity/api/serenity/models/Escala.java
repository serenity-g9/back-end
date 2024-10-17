package com.serenity.api.serenity.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Escala  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Integer funcaoEscala;
    private Integer qtdColaborador;
    private Integer qtdHora;
    private Double valor;
    private Boolean comissionado;
    private Boolean asoObrigatorio;

    @ManyToOne
    private Demanda demanda;
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s",
                id,  // ID da Escala
                funcaoEscala,  // Função da Escala
                qtdColaborador,  // Quantidade de Colaboradores
                qtdHora,  // Quantidade de Horas
                valor,  // Valor
                comissionado,  // Comissionado (true/false)
                asoObrigatorio,  // ASO Obrigatório (true/false)
                demanda != null ? demanda.getId() : ""  // ID da Demanda, se existir
        );
    }


}

