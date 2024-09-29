package com.serenity.api.serenity.dtos.comissao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ComissaoRequest(
        @NotNull
        Integer idAgendamento,
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
