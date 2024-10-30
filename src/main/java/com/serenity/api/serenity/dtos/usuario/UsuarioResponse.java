package com.serenity.api.serenity.dtos.usuario;

import com.serenity.api.serenity.enums.TipoUsuario;
import com.serenity.api.serenity.models.Usuario;
import com.serenity.api.serenity.models.embeddable.Contato;

import java.util.UUID;

public record UsuarioResponse(
        UUID id,
        String email,
        String tipoUsuario,
        Contato contato
) {
    public UsuarioResponse(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getEmail(),
                TipoUsuario.getValor(usuario.getTipoUsuario()),
                usuario.getContato()
        );
    }
}
