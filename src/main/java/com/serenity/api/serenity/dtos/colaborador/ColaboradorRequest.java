package com.serenity.api.serenity.dtos.colaborador;

import com.serenity.api.serenity.models.Usuario;

public record ColaboradorRequest(
        String regiaoResidencia,
        String intermitente,
        Boolean ASO,
        Usuario usuario
) {
}
