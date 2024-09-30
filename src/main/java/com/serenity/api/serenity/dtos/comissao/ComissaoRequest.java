package com.serenity.api.serenity.dtos.comissao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ComissaoRequest(
        @NotNull
        UUID idAgendamento,
        @NotBlank
        String item,
        @NotNull
        Double valor,
        @NotNull
        Integer quantidade,
        @NotNull
        Integer prazoDias
) {
}
