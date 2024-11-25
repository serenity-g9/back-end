package com.serenity.api.serenity.dtos.agendamento;

import com.serenity.api.serenity.dtos.codigo.CodigoResponse;
import com.serenity.api.serenity.dtos.usuario.UsuarioResponse;
import com.serenity.api.serenity.enums.FuncaoAlocacao;
import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.models.Anexo;
import com.serenity.api.serenity.models.Escala;
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
        Endereco endereco
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
                agendamento.getEscala().getDemanda().getEvento().getEndereco()
        );
    }
}