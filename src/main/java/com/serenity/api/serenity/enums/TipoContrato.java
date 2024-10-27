package com.serenity.api.serenity.enums;

public enum TipoContrato {
    FREELANCER(0, "Freelancer"),
    CONTRATO_INTERMITENTE(1, "Contrato intermitente"),
    CONTRATO_TEMPORARIO(2, "Contrato temporário"),
    TERCEIRIZADO(3, "Terceirizado");

    private final Integer id;
    private final String valor;

    TipoContrato(Integer id, String valor) {
        this.id = id;
        this.valor = valor;
    }

    public static String getValor(int id) {
        for (TipoContrato value : TipoContrato.values()) {
            if (value.id == id) return value.valor;
        }

        return null;
    }
}
