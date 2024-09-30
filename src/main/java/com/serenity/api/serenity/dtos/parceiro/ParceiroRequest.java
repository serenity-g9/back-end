package com.serenity.api.serenity.dtos.parceiro;

import com.serenity.api.serenity.enums.PermissaoUsuario;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ParceiroRequest(
        @NotBlank
        UUID idUsuario,
        @NotBlank
        PermissaoUsuario permissao
) {
}
