package com.serenity.api.serenity.dtos.pagamento;

public record PagamentoRequest(
        Integer idAgendamento,
        Double valor,
        Integer prazoDias
) {
}
