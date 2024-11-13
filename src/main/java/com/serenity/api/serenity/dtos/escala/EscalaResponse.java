package com.serenity.api.serenity.dtos.escala;

import com.serenity.api.serenity.enums.FuncaoAlocacao;
import com.serenity.api.serenity.models.Escala;

import java.util.UUID;

public record EscalaResponse(
        UUID id,
        String funcaoEscala,
        Integer qtdColaborador,
        Integer horasJornada,
        Double valor
) {
    public EscalaResponse(Escala escala) {
        this(
                escala.getId(),
                escala.getFuncaoEscala() != null ? FuncaoAlocacao.getValor(escala.getFuncaoEscala()) : null,
                escala.getQtdColaborador(),
                escala.getHorasJornada(),
                escala.getValor()
        );
    }
}
