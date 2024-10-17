package com.serenity.api.serenity.mappers;

import com.serenity.api.serenity.dtos.agendamento.AgendamentoRequest;
import com.serenity.api.serenity.dtos.agendamento.AgendamentoUpdateRequest;
import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.models.Escala;
import com.serenity.api.serenity.services.EscalaService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper
public abstract class AgendamentoMapper {

    @Autowired
    private EscalaService escalaService;

       @Mapping(target = "escala", source = "idEscala")
    public abstract Agendamento toAgendamento(AgendamentoRequest agendamentoRequest);

    @Mapping(target = "escala", ignore = true)
    @Mapping(target = "registros", ignore = true)
    public abstract Agendamento toAgendamento(AgendamentoUpdateRequest agendamentoUpdateRequest, @MappingTarget Agendamento agendamento);

    protected Escala mapIdEscalaToEscala(UUID idEscala) {
        return escalaService.buscarPorId(idEscala);
    }
}
