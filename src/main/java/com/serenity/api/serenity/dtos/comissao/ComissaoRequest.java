package com.serenity.api.serenity.dtos.comissao;

public record ComissaoRequest(
        Integer idAgendamento,
        String item,
        Double valor,
        Integer quantidade,
        Integer prazoDias
) {
}
