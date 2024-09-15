package com.serenity.api.serenity.dtos.parceiro;

import com.serenity.api.serenity.enums.PermissaoUsuario;

public record ParceiroRequest(
        Integer idUsuario,
        PermissaoUsuario permissao
) {
}
