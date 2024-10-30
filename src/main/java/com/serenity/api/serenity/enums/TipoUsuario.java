package com.serenity.api.serenity.enums;

public enum TipoUsuario {
    PARCEIRO(0, "parceiro"),
    COLABORADOR(1, "colaborador");

    private final Integer id;
    private final String valor;

    TipoUsuario(Integer id, String valor) {
        this.id = id;
        this.valor = valor;
    }

    public static String getValor(int id) {
        for (TipoUsuario value : TipoUsuario.values()) {
            if (value.id == id) return value.valor;
        }

        return null;
    }
}
