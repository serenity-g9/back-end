package com.serenity.api.serenity.dtos.parceiro;

import com.serenity.api.serenity.enums.PermissaoUsuario;
import jakarta.validation.constraints.NotBlank;

public record ParceiroRequest(
        @NotBlank
        Integer idUsuario,
        @NotBlank
        PermissaoUsuario permissao
) {
}
