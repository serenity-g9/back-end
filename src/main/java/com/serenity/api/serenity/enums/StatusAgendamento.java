package com.serenity.api.serenity.enums;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

public enum StatusAgendamento {
    CONFIRMADO(0, "disponivel"),
    PENDENTE(1, "pendente"),
    DISPONIVEL(2, "confirmado"),
    ENCERRADO(3, "encerrado");

    private final Integer id;
    private final String valor;

    StatusAgendamento(int id, String valor) {
        this.id = id;
        this.valor = valor;
    }

    public static String getValor(int id) {
        for (StatusAgendamento value : StatusAgendamento.values()) {
            if (value.id == id) return value.valor;
        }

        return null;
    }

    public static void validarValor(String valor) {
        boolean valid = false;
        for (StatusAgendamento value : StatusAgendamento.values()) {
            if (Objects.equals(value.valor, valor)) {
                valid = true;
                break;
            }
        }

        if (!valid) throw new ResponseStatusException(HttpStatusCode.valueOf(400));
    }
}
