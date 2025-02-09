package com.serenity.api.serenity.dtos.formulario;

import com.serenity.api.serenity.models.Formulario;

import java.time.LocalDateTime;

public record FormularioResponse(
        String id,
        String nome,
        LocalDateTime createdAt,
        LocalDateTime lastModified
) {
    public FormularioResponse(Formulario f) {
        this(
                f.getId(),
                f.getNome(),
                f.getCreatedAt(),
                f.getLastModified()
        );
    }
}
