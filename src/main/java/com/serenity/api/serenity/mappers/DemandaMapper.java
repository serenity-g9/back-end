package com.serenity.api.serenity.mappers;

import com.serenity.api.serenity.dtos.demanda.DemandaRequest;
import com.serenity.api.serenity.dtos.demanda.DemandaUpdateRequest;
import com.serenity.api.serenity.models.Demanda;
import com.serenity.api.serenity.models.Evento;
import com.serenity.api.serenity.models.Usuario;
import com.serenity.api.serenity.services.DemandaService;
import com.serenity.api.serenity.services.EventoService;
import com.serenity.api.serenity.services.UsuarioService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper
public abstract class DemandaMapper {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private UsuarioService usuarioService;

    @Mapping(target = "evento", source = "idEvento")
    @Mapping(target = "responsavel", source = "idResponsavel")
    public abstract Demanda toDemanda(DemandaRequest demandaRequest);

    @Mapping(target = "evento", ignore = true)
    @Mapping(target = "responsavel", ignore = true)
    public abstract Demanda toDemanda(DemandaUpdateRequest demandaRequest, @MappingTarget Demanda demanda);

    protected Evento mapIdEventoToEvento(UUID idEvento) {
        return eventoService.buscarPorId(idEvento);
    }

    protected Usuario madIdUsuarioToUsuario(UUID idUsuario) {
        return idUsuario != null ? usuarioService.buscarPorId(idUsuario) : null;
    }
}
