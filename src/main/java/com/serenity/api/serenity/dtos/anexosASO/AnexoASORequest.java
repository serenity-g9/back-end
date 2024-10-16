package com.serenity.api.serenity.dtos.anexosASO;

import com.serenity.api.serenity.models.Usuario;

import java.time.LocalDate;

public record AnexoASORequest(
        LocalDate dataEmitido,
        String urlAnexo,
        Usuario usuario) {
}
