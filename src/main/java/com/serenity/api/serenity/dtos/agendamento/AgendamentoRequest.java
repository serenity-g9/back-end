package com.serenity.api.serenity.dtos.agendamento;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record AgendamentoRequest(
        @NotNull
        UUID idColaborador,
        @NotNull
        UUID idEscala,
        @Future
    LocalDateTime horarioEntrada
) {
}
