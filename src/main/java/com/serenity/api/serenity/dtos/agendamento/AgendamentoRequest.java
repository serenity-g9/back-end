package com.serenity.api.serenity.dtos.agendamento;

import lombok.Data;

import java.time.LocalDateTime;

public record AgendamentoRequest(
    Integer idColaborador,
    Integer idEscala,
    LocalDateTime horarioEntrada
) {
}
