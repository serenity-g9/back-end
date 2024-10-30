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
public class Pagamento implements Faturavel, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Double valor;
    private LocalDateTime emissao;
    private LocalDate vencimento;
    private Boolean efetuado;

    @OneToOne
    private Agendamento agendamento;

    @Override
    public Double getFaturamento() {
        return efetuado ? valor : 0.0;
    }

    @Override
    public String toString() {
        return String.format("%s;%s%s;%s;%s;%s\n",
                id,
                valor,
                emissao,
                vencimento,
                efetuado,
                agendamento != null ? agendamento.getId() : ""
        );
    }


}
