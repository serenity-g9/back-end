package com.serenity.api.serenity.dtos.escala;

import com.serenity.api.serenity.dtos.usuario.UsuarioResponse;
import com.serenity.api.serenity.enums.FuncaoAlocacao;
import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.models.Escala;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record EscalaResponse(
        UUID id,
        String funcaoEscala,
        Integer qtdColaborador,
        Integer horasJornada,
        Double valor
) {
    public EscalaResponse(Escala escala) {
        this(
                escala.getId(),
                escala.getFuncaoEscala() != null ? FuncaoAlocacao.getValor(escala.getFuncaoEscala()) : null,
                escala.getQtdColaborador(),
                escala.getHorasJornada(),
                escala.getValor()
        );
    }
}

record AgendamentoResponse(
        UUID id,
        LocalDateTime horarioEntrada,
        LocalDateTime horarioSaida,
        String status,
        String funcao
){
    public AgendamentoResponse(Agendamento agendamento) {
        this(
                agendamento.getId(),
                agendamento.getHorarioEntrada(),
                agendamento.getHorarioSaida(),
                agendamento.getStatus(),
                FuncaoAlocacao.getValor(agendamento.getEscala().getFuncaoEscala())
        );
    }
}