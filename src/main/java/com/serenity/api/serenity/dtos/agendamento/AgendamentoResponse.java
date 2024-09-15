package com.serenity.api.serenity.dtos.agendamento;

import com.serenity.api.serenity.models.Agendamento;

import java.time.LocalDateTime;

public record AgendamentoResponse(
        LocalDateTime horarioEntrada
) {
    public AgendamentoResponse(Agendamento agendamento) {
        this(
                agendamento.getHorarioEntrada()
        );
    }
}
