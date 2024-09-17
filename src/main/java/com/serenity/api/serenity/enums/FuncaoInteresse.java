package com.serenity.api.serenity.enums;

public enum FuncaoInteresse {
    MOCHILEIRO(0, "Mochileiro/Ambulante com Mochila BAG"),
    ATENDENTE_DE_BAR(1, "Atendente de bar"),
    DOSADOR(2, "Dosador"),
    GARCOM(3, "Garçom"),
    OPERADOR_DE_CAIXA(4, "Operador de caixa"),
    REPOSITOR(5, "Repositor"),
    CARREGADOR(6, "Carregador"),
    OUTRO(7, "Outro");

    private final Integer id;
    private final String valor;

    FuncaoInteresse(Integer id, String valor) {
        this.id = id;
        this.valor = valor;
    }

    public static String getValor(int id) {
        for (FuncaoInteresse value : FuncaoInteresse.values()) {
            if (value.id == id) return value.valor;
        }

        return null;
    }
}
