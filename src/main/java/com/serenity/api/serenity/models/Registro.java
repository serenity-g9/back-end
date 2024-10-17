package com.serenity.api.serenity.models;

import com.serenity.api.serenity.enums.TipoRegistro;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Registro implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private Agendamento agendamento;

    @Enumerated(EnumType.STRING)
    private TipoRegistro tipoRegistro;
    private LocalDateTime dataHorario;

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s",
                id,
                agendamento != null ? agendamento.getId() : "",
                tipoRegistro != null ? tipoRegistro.name() : "",
                dataHorario
        );
    }

}
