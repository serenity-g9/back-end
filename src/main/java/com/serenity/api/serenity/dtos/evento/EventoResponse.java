package com.serenity.api.serenity.dtos.evento;

import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import com.serenity.api.serenity.models.Evento;

import java.time.LocalDate;
import java.util.UUID;

public record EventoResponse(
        UUID id,
        String nome,
        Double orcamento,
        LocalDate inicio,
        LocalDate fim
) {
    public EventoResponse(Evento evento) {
        this(
                evento.getId(),
                evento.getNome(),
                evento.getOrcamento(),
                evento.getInicio(),
                evento.getFim()
        );
    }
}
