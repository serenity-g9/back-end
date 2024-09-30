package com.serenity.api.serenity.mappers;

import com.serenity.api.serenity.dtos.agendamento.AgendamentoRequest;
import com.serenity.api.serenity.dtos.agendamento.AgendamentoUpdateRequest;
import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.models.Colaborador;
import com.serenity.api.serenity.models.Escala;
import com.serenity.api.serenity.services.ColaboradorService;
import com.serenity.api.serenity.services.EscalaService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper
public abstract class AgendamentoMapper {

    @Autowired
    private ColaboradorService colaboradorService;

    @Autowired
    private EscalaService escalaService;

    @Mapping(target = "colaborador", source = "idColaborador")
    @Mapping(target = "escala", source = "idEscala")
    public abstract Agendamento toAgendamento(AgendamentoRequest agendamentoRequest);

    @Mapping(target = "colaborador", ignore = true)
    @Mapping(target = "escala", ignore = true)
    @Mapping(target = "registros", ignore = true)
    public abstract Agendamento toAgendamento(AgendamentoUpdateRequest agendamentoUpdateRequest, @MappingTarget Agendamento agendamento);

    protected Colaborador mapIdColaboradorToColaborador(UUID idColaborador) {
        return colaboradorService.buscarPorId(idColaborador);
    }

    protected Escala mapIdEscalaToEscala(UUID idEscala) {
        return escalaService.buscarPorId(idEscala);
    }
}
