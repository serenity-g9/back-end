package com.serenity.api.serenity.dtos.pagamento;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PagamentoRequest(
        @NotBlank
        UUID idAgendamento,
        @NotBlank
        Double valor,
        @NotNull
        Integer prazoDias
) {
}
