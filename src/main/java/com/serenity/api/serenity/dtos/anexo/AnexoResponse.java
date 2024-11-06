package com.serenity.api.serenity.dtos.anexo;

import com.serenity.api.serenity.enums.TipoAnexo;
import com.serenity.api.serenity.models.Anexo;

import java.util.UUID;

public record AnexoResponse(
        UUID id,
        String nome,
        String url,
        String tipo
) {
    public AnexoResponse(Anexo anexo) {
        this(
                anexo.getId(),
                anexo.getNome(),
                anexo.getUrl(),
                TipoAnexo.getValor(anexo.getTipoArquivo())
        );
    }
}
