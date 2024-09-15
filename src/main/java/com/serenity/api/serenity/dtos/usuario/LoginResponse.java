package com.serenity.api.serenity.dtos.usuario;

import com.serenity.api.serenity.models.Usuario;

public record LoginResponse(
        Integer id,
        String email,
        String nome
) {
    public LoginResponse(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getNome()
        );
    }
}
