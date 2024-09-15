package com.serenity.api.serenity.dtos.colaborador;

public record ColaboradorUpdateRequest(
        String regiaoResidencia,
        String intermitente,
        Boolean ASO
) {
}
