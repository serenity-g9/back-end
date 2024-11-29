package com.serenity.api.serenity.dtos.agendamento;

import com.serenity.api.serenity.dtos.codigo.CodigoResponse;
import com.serenity.api.serenity.dtos.usuario.UsuarioResponse;
import com.serenity.api.serenity.enums.FuncaoAlocacao;
import com.serenity.api.serenity.models.*;
import com.serenity.api.serenity.models.embeddable.Endereco;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public record AgendamentoResponse(
        UUID id,
        LocalDateTime horarioEntrada,
        LocalDateTime horarioSaida,
        LocalDateTime createdAt,
        LocalDateTime lastModified,
        LocalDateTime horarioInvitacaoAceito,
        String status,
        UsuarioResponse usuario,
        CodigoResponse codigo,
        String nome,
        String funcao,
        Double cache,
        Anexo imagem,
        Endereco endereco,
        EscalaResponse escala
) {
    public AgendamentoResponse(Agendamento agendamento) {
        this(
                agendamento.getId(),
                agendamento.getHorarioEntrada(),
                agendamento.getHorarioSaida(),
                agendamento.getCreatedAt(),
                agendamento.getLastModified(),
                agendamento.getHorarioInvitacaoAceito(),
                agendamento.getStatus(),
                Optional.ofNullable(agendamento.getUsuario())
                        .map(UsuarioResponse::new)
                        .orElse(null),
                Optional.ofNullable(agendamento.getCodEntrada())
                        .map(CodigoResponse::new)
                        .orElse(null),
                agendamento.getEscala().getDemanda().getEvento().getNome(),
                FuncaoAlocacao.getValor(agendamento.getEscala().getFuncaoEscala()),
                agendamento.getEscala().getValor(),
                agendamento.getEscala().getDemanda().getEvento().getImagem(),
                agendamento.getEscala().getDemanda().getEvento().getEndereco(),
                new EscalaResponse(agendamento.getEscala())
        );
    }
}

record EscalaResponse(
        UUID id,
        String funcao,
        DemandaResponse demanda
) {
    public EscalaResponse(Escala escala) {
        this(
                escala.getId(),
                FuncaoAlocacao.getValor(escala.getFuncaoEscala()),
                new DemandaResponse(escala.getDemanda())
        );
    }
}

record DemandaResponse(
        UUID id,
        String nome,
        EventoResponse evento
) {
    public DemandaResponse(Demanda demanda) {
        this(
                demanda.getId(),
                demanda.getNome(),
                new EventoResponse(demanda.getEvento())
        );
    }
}

record EventoResponse(
        UUID id,
        String nome
) {
    public EventoResponse(Evento evento) {
        this(
                evento.getId(),
                evento.getNome()
        );
    }
}