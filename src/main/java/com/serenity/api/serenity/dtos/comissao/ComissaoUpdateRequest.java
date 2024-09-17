package com.serenity.api.serenity.dtos.comissao;

public record ComissaoUpdateRequest(
        String item,
        Double valor,
        Integer quantidade,
        Integer prazoDias
) {
}
