package com.serenity.api.serenity.dtos.usuario;

import com.serenity.api.serenity.models.Usuario;

import java.util.UUID;

public record UsuarioResponse(
        UUID id,
        String email
) {
    public UsuarioResponse(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getEmail()
        );
    }
}
