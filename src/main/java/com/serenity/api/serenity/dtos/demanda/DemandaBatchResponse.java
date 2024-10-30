package com.serenity.api.serenity.dtos.demanda;

import com.serenity.api.serenity.dtos.escala.EscalaResponse;
import com.serenity.api.serenity.dtos.evento.EventoResponse;
import com.serenity.api.serenity.dtos.usuario.UsuarioResponse;
import com.serenity.api.serenity.enums.TipoContrato;
import com.serenity.api.serenity.models.Demanda;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record DemandaBatchResponse(
        UUID id,
        String nome,
        Double custoTotal,
        LocalDate inicio,
        LocalDate fim,
        String tipoContrato,
        UsuarioResponse responsavel,
        List<EscalaResponse> escalas
) {
    public DemandaBatchResponse(Demanda demanda) {
        this(
            demanda.getId(),
            demanda.getNome(),
            demanda.getCustoTotal(),
            demanda.getInicio(),
            demanda.getFim(),
            TipoContrato.getValor(demanda.getTipoContrato()),
            demanda.getResponsavel() != null ? new UsuarioResponse(demanda.getResponsavel()) : null,
            demanda.getEscalas().isEmpty() ? null :demanda.getEscalas().stream().map(EscalaResponse::new).toList()
        );
    }
}
