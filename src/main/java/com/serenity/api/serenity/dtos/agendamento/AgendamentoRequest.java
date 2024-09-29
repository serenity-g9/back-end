package com.serenity.api.serenity.dtos.agendamento;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoRequest(
    @NotNull
    Integer idColaborador,
    @NotNull
    Integer idEscala,
    @Future
    LocalDateTime horarioEntrada
) {
}
