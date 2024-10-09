package com.serenity.api.serenity.mappers;

import com.serenity.api.serenity.dtos.usuario.UsuarioRequest;
import com.serenity.api.serenity.dtos.usuario.UsuarioUpdateRequest;
import com.serenity.api.serenity.models.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public abstract class UsuarioMapper {
    public abstract Usuario toUsuario(UsuarioRequest usuarioRequest);
    public abstract Usuario toUsuario(UsuarioUpdateRequest usuarioUpdateRequest, @MappingTarget Usuario usuario);

}
