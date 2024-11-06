package com.serenity.api.serenity.dtos.evento;

import com.serenity.api.serenity.models.Evento;

import java.time.LocalDateTime;

public record EventoExportResponse(
        String nome,
        Double orcamento,
        LocalDateTime inicio,
        LocalDateTime fim,
        String status,
        String endereco,
        String cidade,
        String cep,
        String nomeFormulario,
        String urlFormulario,
        String responsavel,
        String emailResponsavel
) {
    public EventoExportResponse(Evento evento) {
        this(
                evento.getNome(),
                evento.getOrcamento(),
                evento.getInicio(),
                evento.getFim(),
                evento.getInicio().isAfter(LocalDateTime.now())
                        ? "Não iniciado"
                        : evento.getFim().isAfter(LocalDateTime.now())
                        ? "Em andamento"
                        : "Finalizado",
                String.format("%s, %s", evento.getEndereco() == null ? "" : evento.getEndereco().getLogradouro(), evento.getEndereco() == null ? "" : evento.getEndereco().getNumero()),
                String.format("%s - %s", evento.getEndereco() == null ? "" : evento.getEndereco().getCidade(), evento.getEndereco() == null ? "" : evento.getEndereco().getUf()),
                evento.getEndereco() == null ? "" : evento.getEndereco().getCep(),
                evento.getFormulario() == null ? "" : evento.getFormulario().getNome(),
                evento.getFormulario() == null ? "" : evento.getFormulario().getUrl(),
                evento.getResponsavel() == null ? "" : evento.getResponsavel().getContato().getNome(),
                evento.getResponsavel() == null ? "" : evento.getResponsavel().getEmail()
        );
    }
}
