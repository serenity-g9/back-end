package com.serenity.api.serenity.dtos.escala;

import jakarta.validation.constraints.NotNull;

public record EscalaBatchRequest(
        @NotNull
        Integer funcaoEscala,
        @NotNull
        Integer qtdColaborador,
        @NotNull
        Integer horasJornada,
        @NotNull
        Double valor
) {
}
