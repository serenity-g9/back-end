package com.serenity.api.serenity.dtos.autenticacao;

import com.serenity.api.serenity.models.Usuario;

public record AccessTokenResponse(
        Integer id,
        String email,
        String token
) {
    public AccessTokenResponse(Usuario usuario, String token) {
        this (
                usuario.getId(),
                usuario.getEmail(),
                token
        );
    }
}
