package com.serenity.api.serenity.dtos.parceiro;

import com.serenity.api.serenity.enums.PermissaoUsuario;
import com.serenity.api.serenity.models.Parceiro;
import com.serenity.api.serenity.models.Usuario;

public record ParceiroResponse(
        Usuario usuario,
        String permissao
) {
    public ParceiroResponse(Parceiro parceiro) {
        this(
                parceiro.getUsuario(),
                PermissaoUsuario.getValor(parceiro.getPermissao())
        );
    }
}
