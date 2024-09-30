package com.serenity.api.serenity.mappers;

import com.serenity.api.serenity.dtos.colaborador.ColaboradorRequest;
import com.serenity.api.serenity.dtos.colaborador.ColaboradorUpdateRequest;
import com.serenity.api.serenity.models.Colaborador;
import com.serenity.api.serenity.models.Usuario;
import com.serenity.api.serenity.services.UsuarioService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper
public abstract class ColaboradorMapper {

    @Autowired
    private UsuarioService usuarioService;

    @Mapping(target = "usuario", source = "idUsuario")
    public abstract Colaborador toColaborador(ColaboradorRequest colaboradorRequest);

    @Mapping(target = "usuario", ignore = true)
    public abstract Colaborador toColaborador(ColaboradorUpdateRequest colaboradorRequest, @MappingTarget Colaborador colaborador);

    protected Usuario mapIdUsuarioToUsuario(UUID idUsuario) {
        return usuarioService.buscarPorId(idUsuario);
    }
}
