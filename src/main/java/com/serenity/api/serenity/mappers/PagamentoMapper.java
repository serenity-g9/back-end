package com.serenity.api.serenity.mappers;

import com.serenity.api.serenity.dtos.pagamento.PagamentoRequest;
import com.serenity.api.serenity.dtos.pagamento.PagamentoUpdateRequest;
import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.models.Pagamento;
import com.serenity.api.serenity.services.AgendamentoService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Mapper
public abstract class PagamentoMapper {

    @Autowired
    private AgendamentoService agendamentoService;

    @Mapping(target = "agendamento", source = "idAgendamento")
    @Mapping(target = "emissao", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "vencimento", expression = "java(calcularVencimento(pagamentoRequest.prazoDias()))")
    public abstract Pagamento toPagamento(PagamentoRequest pagamentoRequest);

    @Mapping(target = "agendamento", ignore = true)
    public abstract Pagamento toPagamento(PagamentoUpdateRequest pagamentoRequest, @MappingTarget Pagamento pagamento);

    protected Agendamento mapIdAgendamentoToAgendamento(Integer idPagamento) {
        return agendamentoService.buscarPorId(idPagamento);
    }

    protected LocalDate calcularVencimento(Integer prazoDias) {
        return prazoDias == null ? LocalDate.now() : LocalDate.now().plusDays(prazoDias);
    }
}
