package com.serenity.api.serenity.dtos.usuario;

import com.serenity.api.serenity.enums.TipoLogin;
import com.serenity.api.serenity.models.Usuario;

import java.util.Set;

public record LoginResponse(
        Integer id,
        String email,
        String nome,
        Set<TipoLogin> tiposLogin
) {
    public LoginResponse(Usuario usuario, Set<TipoLogin> tiposLogin) {
        this(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getNome(),
                tiposLogin
        );
    }
}
