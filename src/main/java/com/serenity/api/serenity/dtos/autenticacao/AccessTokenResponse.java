package com.serenity.api.serenity.dtos.autenticacao;

import com.serenity.api.serenity.dtos.anexo.AnexoResponse;
import com.serenity.api.serenity.enums.TipoUsuario;
import com.serenity.api.serenity.models.Usuario;
import com.serenity.api.serenity.models.embeddable.Contato;

import java.util.UUID;

public record AccessTokenResponse(
        UUID id,
        String email,
        String tipoUsuario,
        String token,
        AnexoResponse imagem,
        Contato contato
) {
    public AccessTokenResponse(Usuario usuario, String token) {
        this (
                usuario.getId(),
                usuario.getEmail(),
                TipoUsuario.getValor(usuario.getTipoUsuario()),
                token,
                usuario.getImagem() == null ? null : new AnexoResponse(usuario.getImagem()),
                usuario.getContato()
        );
    }
}
