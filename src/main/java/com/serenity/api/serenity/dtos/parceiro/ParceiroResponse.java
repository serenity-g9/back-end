package com.serenity.api.serenity.dtos.parceiro;

import com.serenity.api.serenity.enums.PermissaoUsuario;
import com.serenity.api.serenity.models.Parceiro;
import com.serenity.api.serenity.models.Usuario;

import java.util.UUID;

public record ParceiroResponse(
        UUID id,
        Usuario usuario,
        PermissaoUsuario permissao
) {
    public ParceiroResponse(Parceiro parceiro) {
        this(
                parceiro.getId(),
                parceiro.getUsuario(),
                parceiro.getPermissao()
        );
    }
}
