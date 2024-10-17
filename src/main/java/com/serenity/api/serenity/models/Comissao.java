package com.serenity.api.serenity.models;

import com.serenity.api.serenity.interfaces.Faturavel;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comissao  implements Faturavel, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

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

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s",
                id,  // ID
                item,  // Item
                valor,  // Valor
                quantidade,  // Quantidade
                emissao,  // Data de emissão
                vencimento,  // Data de vencimento
                efetuado,  // Status (efetuado ou não)
                agendamento != null ? agendamento.getId() : ""  // ID do Agendamento, se existir
        );
    }


}
