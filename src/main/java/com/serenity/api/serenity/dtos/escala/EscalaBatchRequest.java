package com.serenity.api.serenity.dtos.escala;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EscalaBatchRequest(
        @NotNull
        Integer funcaoEscala,
        @NotNull
        Integer qtdColaborador,
        @NotNull
        Integer qtdHora,
        @NotNull
        Double valor
) {
}