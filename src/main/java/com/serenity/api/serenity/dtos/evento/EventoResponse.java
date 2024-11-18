package com.serenity.api.serenity.dtos.evento;

import com.serenity.api.serenity.dtos.anexo.AnexoResponse;
import com.serenity.api.serenity.dtos.formulario.FormularioResponse;
import com.serenity.api.serenity.dtos.usuario.UsuarioResponse;
import com.serenity.api.serenity.models.Evento;
import com.serenity.api.serenity.models.embeddable.Endereco;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventoResponse(
        UUID id,
        String nome,
        Double orcamento,
        LocalDateTime inicio,
        LocalDateTime fim,
        Endereco endereco,
        AnexoResponse imagem,
        FormularioResponse formulario,
        UsuarioResponse responsavel,
        String status,
        LocalDateTime createdAt,
        LocalDateTime lastModified
) {
    public EventoResponse(Evento evento) {
        this(
                evento.getId(),
                evento.getNome(),
                evento.getOrcamento(),
                evento.getInicio(),
                evento.getFim(),
                evento.getEndereco(),
                evento.getImagem() == null ? null : new AnexoResponse(evento.getImagem()),
                evento.getFormulario() == null ? null : new FormularioResponse(evento.getFormulario()),
                evento.getResponsavel() == null ? null : new UsuarioResponse(evento.getResponsavel()),
                evento.getInicio().isAfter(LocalDateTime.now())
                    ? "Não iniciado"
                    : evento.getFim().isAfter(LocalDateTime.now())
                        ? "Em andamento"
                        : "Finalizado",
                evento.getCreatedAt(),
                evento.getLastModified()
        );
    }
}
