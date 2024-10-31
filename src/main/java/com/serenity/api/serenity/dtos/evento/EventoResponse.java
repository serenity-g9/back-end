package com.serenity.api.serenity.dtos.evento;

import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import com.serenity.api.serenity.dtos.demanda.DemandaBatchResponse;
import com.serenity.api.serenity.dtos.demanda.DemandaResponse;
import com.serenity.api.serenity.dtos.formulario.FormularioResponse;
import com.serenity.api.serenity.dtos.usuario.UsuarioResponse;
import com.serenity.api.serenity.models.Evento;
import com.serenity.api.serenity.models.Imagem;
import com.serenity.api.serenity.models.embeddable.Endereco;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record EventoResponse(
        UUID id,
        String nome,
        Double orcamento,
        LocalDateTime inicio,
        LocalDateTime fim,
        Endereco endereco,
        Imagem imagem,
        FormularioResponse formulario,
        UsuarioResponse responsavel,
        String status
) {
    public EventoResponse(Evento evento) {
        this(
                evento.getId(),
                evento.getNome(),
                evento.getOrcamento(),
                evento.getInicio(),
                evento.getFim(),
                evento.getEndereco(),
                evento.getImagem(),
                evento.getFormulario() == null ? null : new FormularioResponse(evento.getFormulario()),
                evento.getResponsavel() == null ? null : new UsuarioResponse(evento.getResponsavel()),
                evento.getInicio().isAfter(LocalDateTime.now())
                    ? "Não iniciado"
                    : evento.getFim().isAfter(LocalDateTime.now())
                        ? "Em andamento"
                        : "Finalizado"
        );
    }
}
