package com.serenity.api.serenity.dtos.evento;

import com.serenity.api.serenity.models.Evento;

import java.time.LocalDate;

public record EventoResponse(
        Integer id,
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
