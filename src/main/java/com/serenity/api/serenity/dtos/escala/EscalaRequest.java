package com.serenity.api.serenity.dtos.escala;

public record EscalaRequest(
        Integer funcaoEscala,
        Integer qtdColaborador,
        Integer qtdHora,
        Double valor,
        Boolean comissionado,
        Boolean asoObrigatorio,
        Integer idEvento
) {
}
