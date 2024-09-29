package com.serenity.api.serenity.dtos.escala;

import jakarta.validation.constraints.NotNull;

public record EscalaUpdateRequest(
        @NotNull
        Integer funcaoEscala,
        @NotNull
        Integer qtdColaborador,
        @NotNull
        Integer qtdHora,
        @NotNull
        Double valor,
        Boolean comissionado,
        Boolean asoObrigatorio
) {
}
