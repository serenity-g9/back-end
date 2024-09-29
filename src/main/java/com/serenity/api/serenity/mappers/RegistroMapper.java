package com.serenity.api.serenity.mappers;

import com.serenity.api.serenity.dtos.registro.RegistroRequest;
import com.serenity.api.serenity.models.Agendamento;
import com.serenity.api.serenity.models.Registro;
import com.serenity.api.serenity.services.AgendamentoService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class RegistroMapper {

    @Autowired
    private AgendamentoService agendamentoService;

    @Mapping(target = "agendamento", source = "idAgendamento")
    @Mapping(target = "dataHorario", expression = "java(java.time.LocalDateTime.now())")
    public abstract Registro toRegistro(RegistroRequest registroRequest);

    protected Agendamento mapIdAgendamentoToAgendamento(Integer idAgendamento) {
        return agendamentoService.buscarPorId(idAgendamento);
    }
}
