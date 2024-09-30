package com.serenity.api.serenity.mappers;

import com.serenity.api.serenity.dtos.comissao.ComissaoRequest;
import com.serenity.api.serenity.dtos.comissao.ComissaoUpdateRequest;
import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.models.Comissao;
import com.serenity.api.serenity.services.AgendamentoService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.UUID;

@Mapper
public abstract class ComissaoMapper {

    @Autowired
    private AgendamentoService agendamentoService;

    @Mapping(target = "agendamento", source = "idAgendamento")
    @Mapping(target = "emissao", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "vencimento", expression = "java(calcularVencimento(comissaoRequest.prazoDias()))")
    public abstract Comissao toComissao(ComissaoRequest comissaoRequest);

    @Mapping(target = "agendamento", ignore = true)
    public abstract Comissao toComissao(ComissaoUpdateRequest comissaoRequest, @MappingTarget Comissao comissao);

    protected Agendamento mapIdAgendamentoToAgendamento(UUID idComissao) {
        return agendamentoService.buscarPorId(idComissao);
    }

    protected LocalDate calcularVencimento(Integer prazoDias) {
        return prazoDias == null ? LocalDate.now() : LocalDate.now().plusDays(prazoDias);
    }
}
