package com.serenity.api.serenity.dtos.autenticacao;

import com.serenity.api.serenity.enums.TipoUsuario;
import com.serenity.api.serenity.models.Usuario;
import com.serenity.api.serenity.models.embeddable.Contato;

import java.util.UUID;

public record AccessTokenResponse(
        UUID id,
        String email,
        String tipoUsuario,
        String token,
        Contato contato
) {
    public AccessTokenResponse(Usuario usuario, String token) {
        this (
                usuario.getId(),
                usuario.getEmail(),
                TipoUsuario.getValor(usuario.getTipoUsuario()),
                token,
                usuario.getContato()
        );
    }
}
