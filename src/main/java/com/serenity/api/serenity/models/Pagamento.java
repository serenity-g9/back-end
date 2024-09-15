package com.serenity.api.serenity.models;

import com.serenity.api.serenity.interfaces.Faturavel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Pagamento implements Faturavel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Double valor;
    private LocalDateTime emissao;
    private LocalDate vencimento;
    private Boolean efetuado;

    @ManyToOne
    private Agendamento agendamento;

    @Override
    public Double getFaturamento() {
        return efetuado ? valor : 0.0;
    }
}
