package com.serenity.api.serenity.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicadoException extends ResponseStatusException {
    public DuplicadoException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
