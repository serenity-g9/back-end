package com.serenity.api.serenity.dtos.demanda;

import com.serenity.api.serenity.dtos.escala.EscalaBatchRequest;
import com.serenity.api.serenity.dtos.escala.EscalaResponse;
import com.serenity.api.serenity.dtos.evento.EventoResponse;
import com.serenity.api.serenity.dtos.usuario.UsuarioResponse;
import com.serenity.api.serenity.enums.FuncaoAlocacao;
import com.serenity.api.serenity.enums.TipoContrato;
import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.models.Demanda;
import com.serenity.api.serenity.services.DemandaService;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record DemandaResponse(
        UUID id,
        String nome,
        Double custoTotal,
        LocalDateTime inicio,
        LocalDateTime fim,
        String tipoContrato,
        EventoResponse evento,
        UsuarioResponse responsavel,
        List<EscalaResponse> escalas,
        List<AgendamentoResponse> agendamentos
) {
    public DemandaResponse(Demanda demanda) {
        this(
            demanda.getId(),
            demanda.getNome(),
            demanda.getCustoTotal(),
            demanda.getInicio(),
            demanda.getFim(),
            TipoContrato.getValor(demanda.getTipoContrato()),
            demanda.getEvento() != null ? new EventoResponse(demanda.getEvento()) : null,
            demanda.getResponsavel() != null ? new UsuarioResponse(demanda.getResponsavel()) : null,
            demanda.getEscalas().isEmpty() ? null :demanda.getEscalas().stream().map(EscalaResponse::new).toList(),
            demanda.getEscalas() == null ? null : demanda.getEscalas().stream()
                    .flatMap(escala -> escala.getAgendamentos().stream())
                    .map(AgendamentoResponse::new)
                    .toList()
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