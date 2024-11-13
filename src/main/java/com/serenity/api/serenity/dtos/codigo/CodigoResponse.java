package com.serenity.api.serenity.dtos.codigo;

import com.serenity.api.serenity.models.Codigo;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

public record CodigoResponse(
        UUID id,
        String digito,
        String imagemQRCode,
        LocalDateTime horarioUtilizado,
        LocalDateTime createdAt,
        LocalDateTime lastModified
) {
    public CodigoResponse(Codigo codigo) {
        this(
                codigo.getId(),
                codigo.getDigito(),
                codigo.getImagemQRCode() == null ? null : Base64.getEncoder().encodeToString(codigo.getImagemQRCode()),
                codigo.getHorarioUtilizado(),
                codigo.getCreatedAt(),
                codigo.getLastModified()
        );
    }
}
