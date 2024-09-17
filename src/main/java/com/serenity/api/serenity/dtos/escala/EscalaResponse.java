package com.serenity.api.serenity.dtos.escala;

import com.serenity.api.serenity.enums.FuncaoAlocacao;
import com.serenity.api.serenity.models.Escala;

public record EscalaResponse(
        Integer id,
        String funcaoEscala,
        Integer qtdColaborador,
        Integer qtdHora,
        Double valor,
        Boolean comissionado,
        Boolean asoObrigatorio
) {
    public EscalaResponse(Escala escala) {
        this(
                escala.getId(),
                FuncaoAlocacao.getValor(escala.getFuncaoEscala()),
                escala.getQtdColaborador(),
                escala.getQtdHora(),
                escala.getValor(),
                escala.getComissionado(),
                escala.getAsoObrigatorio()
        );
    }
}
