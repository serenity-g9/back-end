package com.serenity.api.serenity.dtos.usuario;

public record UsuarioUpdateRequest(
        String email,
        String senha
) {
}
