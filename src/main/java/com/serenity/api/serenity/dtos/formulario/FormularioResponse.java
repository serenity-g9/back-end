package com.serenity.api.serenity.dtos.formulario;

import com.serenity.api.serenity.models.Formulario;

import java.util.UUID;

public record FormularioResponse(
        UUID id,
        String nome,
        String url
) {
    public FormularioResponse(Formulario f) {
        this(
                f.getId(),
                f.getNome(),
                f.getUrl()
        );
    }
}
