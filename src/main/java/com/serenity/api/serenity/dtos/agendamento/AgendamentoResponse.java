package com.serenity.api.serenity.dtos.agendamento;

import com.serenity.api.serenity.dtos.registro.RegistroResponse;
import com.serenity.api.serenity.models.Agendamento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record AgendamentoResponse(
        Integer id,
        LocalDateTime horarioEntrada,
        List<RegistroResponse> registros
) {
    public AgendamentoResponse(Agendamento agendamento) {
        this(
                agendamento.getId(),
                agendamento.getHorarioEntrada(),
                agendamento.getRegistros() == null
                        ? List.of() :
                        agendamento.getRegistros().stream()
                        .map(RegistroResponse::new)
                        .collect(Collectors.toList())
        );
    }
}
