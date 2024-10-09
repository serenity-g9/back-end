package com.serenity.api.serenity.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class CampoDuplicadoExecption extends RuntimeException {
    public CampoDuplicadoExecption(String campo) {
        super("O "+campo+" possui valor duplicado");
    }
}
