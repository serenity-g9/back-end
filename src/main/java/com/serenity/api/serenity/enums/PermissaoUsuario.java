package com.serenity.api.serenity.enums;

public enum PermissaoUsuario {
    SUPER(0, "Super");

    private final int id;
    private final String valor;

    PermissaoUsuario(int id, String valor) {
        this.id = id;
        this.valor = valor;
    }
}
