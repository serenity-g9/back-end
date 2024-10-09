package com.serenity.api.serenity.dtos.agendamento;

import jakarta.validation.constraints.Future;

import java.time.LocalDateTime;

public record AgendamentoUpdateRequest(
    @Future
    LocalDateTime horarioEntrada
) {
}
