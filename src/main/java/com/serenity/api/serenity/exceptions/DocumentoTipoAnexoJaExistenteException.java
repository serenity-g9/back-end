package com.serenity.api.serenity.exceptions;

import com.serenity.api.serenity.enums.TipoAnexo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DocumentoTipoAnexoJaExistenteException extends ResponseStatusException {
    public DocumentoTipoAnexoJaExistenteException(TipoAnexo tipoAnexo) {
        super(HttpStatus.BAD_REQUEST, "Documento já existe com o seguinte tipo: " + tipoAnexo.toString());
    }
}
