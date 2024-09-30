package com.serenity.api.serenity.dtos.autenticacao;

import com.serenity.api.serenity.models.Usuario;

import java.util.UUID;

public record AccessTokenResponse(
        UUID id,
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
