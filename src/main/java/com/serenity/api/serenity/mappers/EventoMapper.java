package com.serenity.api.serenity.mappers;

import com.serenity.api.serenity.dtos.evento.EventoRequest;
import com.serenity.api.serenity.dtos.evento.EventoUpdateRequest;
import com.serenity.api.serenity.models.Evento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public abstract class EventoMapper {

    public abstract Evento toEvento(EventoRequest eventoRequest);
    public abstract Evento toEvento(EventoUpdateRequest eventoRequest, @MappingTarget Evento evento);
}
