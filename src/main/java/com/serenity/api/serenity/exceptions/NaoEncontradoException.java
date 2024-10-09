package com.serenity.api.serenity.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NaoEncontradoException extends RuntimeException {
    public NaoEncontradoException(String objeto) {
        super("O objeto "+objeto+" não foi encontrado(a)");
    }
}
