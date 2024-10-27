package com.serenity.api.serenity.mappers;

import com.serenity.api.serenity.dtos.escala.EscalaRequest;
import com.serenity.api.serenity.dtos.escala.EscalaUpdateRequest;
import com.serenity.api.serenity.models.Demanda;
import com.serenity.api.serenity.models.Escala;
import com.serenity.api.serenity.models.Evento;
import com.serenity.api.serenity.services.DemandaService;
import com.serenity.api.serenity.services.EventoService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper
public abstract class EscalaMapper {

    @Autowired
    private DemandaService demandaService;

    @Mapping(target = "demanda", source = "idDemanda")
    public abstract Escala toEscala(EscalaRequest escalaRequest);

    @Mapping(target = "demanda", ignore = true)
    public abstract Escala toEscala(EscalaUpdateRequest escalaRequest, @MappingTarget Escala escala);

    protected Demanda mapIdEventoToEvento(UUID idDemanda) {
        return demandaService.buscarPorId(idDemanda);
    }
}