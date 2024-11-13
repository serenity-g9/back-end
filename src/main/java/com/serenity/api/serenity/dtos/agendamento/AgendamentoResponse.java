package com.serenity.api.serenity.dtos.agendamento;

import com.serenity.api.serenity.dtos.codigo.CodigoResponse;
import com.serenity.api.serenity.dtos.usuario.UsuarioResponse;
import com.serenity.api.serenity.models.Agendamento;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public record AgendamentoResponse(
        UUID id,
        LocalDateTime horarioEntrada,
        LocalDateTime createdAt,
        LocalDateTime lastModified,
        UsuarioResponse usuario,
        CodigoResponse codigo
) {
    public AgendamentoResponse(Agendamento agendamento) {
        this(
                agendamento.getId(),
                agendamento.getHorarioEntrada(),
                agendamento.getCreatedAt(),
                agendamento.getLastModified(),
                Optional.ofNullable(agendamento.getUsuario())
                        .map(UsuarioResponse::new)
                        .orElse(null),
                Optional.ofNullable(agendamento.getCodEntrada())
                        .map(CodigoResponse::new)
                        .orElse(null)
        );
    }
}
