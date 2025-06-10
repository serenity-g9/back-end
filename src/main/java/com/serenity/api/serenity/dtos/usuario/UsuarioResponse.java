package com.serenity.api.serenity.dtos.usuario;

import com.serenity.api.serenity.dtos.anexo.AnexoResponse;
import com.serenity.api.serenity.enums.TipoUsuario;
import com.serenity.api.serenity.models.Usuario;
import com.serenity.api.serenity.models.embeddable.Contato;

import java.util.UUID;

public record UsuarioResponse(
        UUID id,
        String email,
        String tipoUsuario,
        Boolean ativo,
        AnexoResponse imagem,
        Contato contato
) {
    public UsuarioResponse(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getEmail(),
                TipoUsuario.getValor(usuario.getTipoUsuario()),
                usuario.getAtivo(),
                usuario.getImagem() == null ? null : new AnexoResponse(usuario.getImagem()),
                usuario.getContato()
        );
    }
}
