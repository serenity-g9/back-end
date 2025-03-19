package com.serenity.api.serenity.enums;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
public enum TipoAnexo {
    IMG_EVENTO(0, "img_evento"),
    IMG_PERFIL(1, "img_perfil"),
    ASO(2, "aso"),
    RG(3, "rg");

    private final Integer id;
    private final String valor;

    TipoAnexo(int id, String valor) {
        this.id = id;
        this.valor = valor;
    }

    public static String getValor(int id) {
        for (TipoAnexo value : TipoAnexo.values()) {
            if (value.id == id) return value.valor;
        }

        return null;
    }
    public static List<TipoAnexo> getDocumentos() {
        return List.of(ASO, RG);
    }
}
