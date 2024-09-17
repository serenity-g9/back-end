package com.serenity.api.serenity.dtos.escala;

public record EscalaUpdateRequest(
        Integer funcaoEscala,
        Integer qtdColaborador,
        Integer qtdHora,
        Double valor,
        Boolean comissionado,
        Boolean asoObrigatorio
) {
}
