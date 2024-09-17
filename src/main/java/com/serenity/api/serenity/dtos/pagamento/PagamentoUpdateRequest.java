package com.serenity.api.serenity.dtos.pagamento;

public record PagamentoUpdateRequest(
        Double valor,
        Integer prazoDias
) {
}
