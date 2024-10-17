package com.serenity.api.serenity.mappers;

import com.serenity.api.serenity.dtos.demanda.DemandaRequest;
import com.serenity.api.serenity.dtos.demanda.DemandaUpdateRequest;
import com.serenity.api.serenity.models.Demanda;
import com.serenity.api.serenity.services.DemandaService;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public abstract class DemandaMapper {

    @Autowired
    private DemandaService demandaService;

    @Mapping(target = "demanda", source = "idDemanda")
    public abstract Demanda toDemanda(DemandaRequest demandaRequest);

    @Mapping(target = "demanda", ignore = true)
    public abstract Demanda toDemanda(DemandaUpdateRequest demandaRequest, @MappingTarget Demanda demanda);

    protected Demanda mapIdDemandaToDemanda(UUID idDemanda) {
        return demandaService.buscarPorId(idDemanda);
    }
}
