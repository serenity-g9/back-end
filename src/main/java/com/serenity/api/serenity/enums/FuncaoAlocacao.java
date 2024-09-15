package com.serenity.api.serenity.enums;

public enum FuncaoAlocacao {
    ATENDENTES_DE_BAR(0, "Atendentes de bar"),
    REPOSITORES(1, "Repositores"),
    CARREGADORES(2, "Carregadores"),
    VIGIA_NOTURNO(3, "Vigia noturno"),
    LIMPEZA(4, "Limpeza"),
    GARCOM(5, "Garçom"),
    DOSADOR(6, "Dosador"),
    BARBACK(7, "Barback"),
    LIDER_DE_BAR(8, "Líder de bar"),
    COORDENADOR(9, "Coordenador"),
    RH(10, "RH"),
    APOIO_DE_RH(11, "Apoio de RH");

    private final int id;
    private final String valor;

    FuncaoAlocacao(int id, String valor) {
        this.id = id;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public String getValor() {
        return valor;
    }

    public static String getValor(int id) {
        for (FuncaoAlocacao funcao : FuncaoAlocacao.values()) {
            if (funcao.getId() == id) return funcao.getValor();
        }

        return null;
    }
}
