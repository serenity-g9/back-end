package com.serenity.api.serenity.dtos.usuario;

import com.serenity.api.serenity.models.Usuario;

public record LoginResponse(
        String email,
        String nome
) {
    public LoginResponse(Usuario usuario) {
        this(
          usuario.getEmail(),
          usuario.getNome()
        );
    }
}
