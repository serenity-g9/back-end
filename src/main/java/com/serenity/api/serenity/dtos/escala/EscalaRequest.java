package com.serenity.api.serenity.dtos.escala;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EscalaRequest(
        @NotNull
        Integer funcaoEscala,
        @NotNull
        Integer qtdColaborador,
        @NotNull
        Integer horasJornada,
        @NotNull
        Double valor,
        @NotNull
        UUID idDemanda
) {
}
