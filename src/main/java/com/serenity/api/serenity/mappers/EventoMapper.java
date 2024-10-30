package com.serenity.api.serenity.mappers;

import com.serenity.api.serenity.dtos.evento.EventoRequest;
import com.serenity.api.serenity.dtos.evento.EventoUpdateRequest;
import com.serenity.api.serenity.models.Evento;
import com.serenity.api.serenity.models.Formulario;
import com.serenity.api.serenity.models.Usuario;
import com.serenity.api.serenity.services.FormularioService;
import com.serenity.api.serenity.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper
public abstract class EventoMapper {

    @Autowired
    private FormularioService formularioService;

    @Autowired
    private UsuarioService usuarioService;

    @Mapping(target = "formulario", source = "idFormulario")
    @Mapping(target = "responsavel", source = "idResponsavel")
    public abstract Evento toEvento(EventoRequest eventoRequest);
    public abstract Evento toEvento(EventoUpdateRequest eventoRequest, @MappingTarget Evento evento);

    protected Formulario madIdFormularioToFormulario(UUID idFormulario) {
        return idFormulario != null ? formularioService.buscarPorId(idFormulario) : null;
    }
    protected Usuario madIdUsuarioToUsuario(UUID idUsuario) {
        return idUsuario != null ? usuarioService.buscarPorId(idUsuario) : null;
    }
}
