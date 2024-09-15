package com.serenity.api.serenity.dtos.evento;

import com.serenity.api.serenity.models.Evento;

public record EventoResponse(
        String nome,
        Double orcamento
) {
    public EventoResponse(Evento evento) {
        this(
                evento.getNome(),
                evento.getOrcamento()
        );
    }
}
