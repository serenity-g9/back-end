package com.serenity.api.serenity.dtos.agendamento;

import com.serenity.api.serenity.dtos.registro.RegistroResponse;
import com.serenity.api.serenity.models.Agendamento;

import java.time.LocalDateTime;
import java.util.List;

public record AgendamentoResponse(
        Integer id,
        LocalDateTime horarioEntrada,
        List<RegistroResponse> registros
) {
    public AgendamentoResponse(Agendamento agendamento, List<RegistroResponse> registroResponses) {
        this(
                agendamento.getId(),
                agendamento.getHorarioEntrada(),
                registroResponses
        );
    }
}
