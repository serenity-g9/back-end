package com.serenity.api.serenity.dtos.parceiro;

import jakarta.validation.constraints.NotBlank;

public record ParceiroUpdateRequest(
        @NotBlank
        Integer permissao
) {
}
