package com.serenity.api.serenity.dtos.formulario;

import com.serenity.api.serenity.models.Formulario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record FormularioResponse(
        UUID id,
        String nome,
        String url,
        LocalDateTime createdAt,
        LocalDateTime lastModified
) {
    public FormularioResponse(Formulario f) {
        this(
                f.getId(),
                f.getNome(),
                f.getUrl(),
                f.getCreatedAt(),
                f.getLastModified()
        );
    }
}
