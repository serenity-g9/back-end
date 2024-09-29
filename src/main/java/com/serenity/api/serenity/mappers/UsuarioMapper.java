package com.serenity.api.serenity.mappers;

import com.serenity.api.serenity.dtos.autenticacao.AccessTokenResponse;
import com.serenity.api.serenity.models.Usuario;
import org.mapstruct.Mapper;

@Mapper
public abstract class UsuarioMapper {
    public abstract AccessTokenResponse toUsuarioTokenRequest(Usuario usuario, String token);
}
