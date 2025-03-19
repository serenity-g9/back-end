package com.serenity.api.serenity.dtos.usuario;

import com.serenity.api.serenity.enums.TipoAnexo;
import com.serenity.api.serenity.models.Anexo;
import com.serenity.api.serenity.models.DocumentoUsuario;
import com.serenity.api.serenity.models.Usuario;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
public class DocumentoUsuarioResponse {
    private UUID id;
    private UUID usuario;
    private Anexo anexo;
    private TipoAnexo tipoAnexo;

    public DocumentoUsuarioResponse(DocumentoUsuario documentoUsuario) {
        this.id = documentoUsuario.getId();
        this.usuario = documentoUsuario.getUsuario().getId();
        this.anexo = documentoUsuario.getAnexo();
        this.tipoAnexo = documentoUsuario.getTipoAnexo();
    }
}
