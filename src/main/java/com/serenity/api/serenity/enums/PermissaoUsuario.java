package com.serenity.api.serenity.enums;

public enum PermissaoUsuario {
    SUPER(0, "Super");

    private final int id;
    private final String valor;

    PermissaoUsuario(int id, String valor) {
        this.id = id;
        this.valor = valor;
    }

    public static String getValor(int id) {
        for (PermissaoUsuario value : PermissaoUsuario.values()) {
            if (value.id == id) return value.valor;
        }

        return null;
    }
}
