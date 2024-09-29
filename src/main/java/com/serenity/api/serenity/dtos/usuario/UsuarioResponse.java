package com.serenity.api.serenity.dtos.usuario;

import com.serenity.api.serenity.models.Usuario;

public record UsuarioResponse(
        Integer id,
        String email
) {
    public UsuarioResponse(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getEmail()
        );
    }
}
