package com.serenity.api.serenity.dtos.registro;

import com.serenity.api.serenity.enums.TipoRegistro;
import com.serenity.api.serenity.models.Registro;

import java.time.LocalDateTime;

public record RegistroResponse(
        LocalDateTime dataHorario,
        String tipoRegistro
) {
    public RegistroResponse(Registro registro) {
        this(
                registro.getDataHorario(),
                TipoRegistro.getValor(registro.getTipoRegistro())
        );
    }
}
