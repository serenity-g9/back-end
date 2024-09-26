package com.serenity.api.serenity.models;

import com.serenity.api.serenity.interfaces.Faturavel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comissao implements Faturavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String item;
    private Double valor;
    private Integer quantidade;
    private LocalDateTime emissao;
    private LocalDate vencimento;
    private Boolean efetuado;

    @ManyToOne
    private Agendamento agendamento;

    @Override
    public Double getFaturamento() {
        return efetuado ? quantidade * valor : 0.0;
    }
}
