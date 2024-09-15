package com.serenity.api.serenity.dtos.comissao;

import com.serenity.api.serenity.models.Comissao;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ComissaoResponse(
        Integer id,
        String item,
        Double valor,
        Integer quantidade,
        LocalDateTime emissao,
        LocalDate vencimento,
        Boolean efetuado,
        Double faturamento
) {
    public ComissaoResponse(Comissao comissao) {
        this(
                comissao.getId(),
                comissao.getItem(),
                comissao.getValor(),
                comissao.getQuantidade(),
                comissao.getEmissao(),
                comissao.getVencimento(),
                comissao.getEfetuado(),
                comissao.getFaturamento()
        );
    }
}
