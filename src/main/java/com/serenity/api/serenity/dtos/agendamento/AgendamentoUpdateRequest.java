package com.serenity.api.serenity.dtos.agendamento;

import java.time.LocalDateTime;

public record AgendamentoUpdateRequest(
    LocalDateTime horarioEntrada
) {
}
