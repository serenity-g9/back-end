package com.serenity.api.serenity.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agendamento implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private Escala escala;

    @OneToMany
    private List<Registro> registros;

    private LocalDateTime horarioEntrada;

    public String toString() {
        String registrosStr = registros.stream()
                .map(registro -> registro.getId().toString())
                .reduce((a, b) -> a + ";" + b).orElse("");

        return String.format("%s,%s,%s,%s",
                id,
                escala.getId(),
                registrosStr,
                horarioEntrada
        );
    }
}
