package com.serenity.api.serenity.enums;

public enum StatusRegistro {
    ENTRADA(1, "Entrada"),
    SAIDA(2, "Saída");

    private final int id;
    private final String valor;

    StatusRegistro(int id, String valor) {
        this.id = id;
        this.valor = valor;
    }
}
