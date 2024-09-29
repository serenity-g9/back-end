package com.serenity.api.serenity.mappers;

import com.serenity.api.serenity.dtos.parceiro.ParceiroRequest;
import com.serenity.api.serenity.dtos.parceiro.ParceiroUpdateRequest;
import com.serenity.api.serenity.models.Parceiro;
import com.serenity.api.serenity.models.Usuario;
import com.serenity.api.serenity.services.UsuarioService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class ParceiroMapper {

    @Autowired
    private UsuarioService usuarioService;

    @Mapping(target = "usuario", source = "idUsuario")
    public abstract Parceiro toParceiro(ParceiroRequest parceiroRequest);

    @Mapping(target = "usuario", ignore = true)
    public abstract Parceiro toParceiro(ParceiroUpdateRequest parceiroRequest, @MappingTarget Parceiro parceiro);

    protected Usuario mapIdUsuarioToUsuario(Integer idUsuario) {
        return usuarioService.buscarPorId(idUsuario);
    }
}
