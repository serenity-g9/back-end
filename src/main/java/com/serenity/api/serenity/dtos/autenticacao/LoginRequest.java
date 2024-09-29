package com.serenity.api.serenity.dtos.autenticacao;

import com.serenity.api.serenity.models.Usuario;

public record LoginRequest(
        String email,
        String senha
) {
    public LoginRequest(Usuario usuario) {
        this(
                usuario.getEmail(),
                usuario.getSenha()
        );
    }
}
