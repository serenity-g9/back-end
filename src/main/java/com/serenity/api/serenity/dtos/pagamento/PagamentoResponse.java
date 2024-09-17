package com.serenity.api.serenity.dtos.pagamento;

import com.serenity.api.serenity.models.Pagamento;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PagamentoResponse(
        Integer id,
        Double valor,
        LocalDateTime emissao,
        LocalDate vencimento,
        Boolean efetuado,
        Double faturamento
) {
    public PagamentoResponse(Pagamento pagamento) {
        this(
                pagamento.getId(),
                pagamento.getValor(),
                pagamento.getEmissao(),
                pagamento.getVencimento(),
                pagamento.getEfetuado(),
                pagamento.getFaturamento()
        );
    }
}
