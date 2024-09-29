package com.serenity.api.serenity.dtos.pagamento;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PagamentoRequest(
        @NotBlank
        Integer idAgendamento,
        @NotBlank
        Double valor,
        @NotNull
        Integer prazoDias
) {
}
