package com.serenity.api.serenity.models;

import com.serenity.api.serenity.enums.TipoRegistro;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Agendamento agendamento;

    @Enumerated(EnumType.STRING)
    private TipoRegistro tipoRegistro;
    private LocalDateTime dataHorario;
}
