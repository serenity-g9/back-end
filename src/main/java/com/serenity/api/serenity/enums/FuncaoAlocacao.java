package com.serenity.api.serenity.enums;

public enum FuncaoAlocacao {
    ATENDENTES_DE_BAR(1, "Atendentes de bar"),
    REPOSITORES(2, "Repositores"),
    CARREGADORES(3, "Carregadores"),
    VIGIA_NOTURNO(4, "Vigia noturno"),
    LIMPEZA(5, "Limpeza"),
    GARCOM(6, "Garçom"),
    DOSADOR(7, "Dosador"),
    BARBACK(8, "Barback"),
    LIDER_DE_BAR(9, "Líder de bar"),
    COORDENADOR(10, "Coordenador"),
    RH(11, "RH"),
    APOIO_DE_RH(12, "Apoio de RH");

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

    public static String getValorById(int id) {
        for (FuncaoAlocacao funcao : FuncaoAlocacao.values()) {
            if (funcao.getId() == id) return funcao.getValor();
        }

        return null;
    }
}
