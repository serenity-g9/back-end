package com.serenity.api.serenity.dtos.registro;

import com.serenity.api.serenity.enums.TipoRegistro;
import com.serenity.api.serenity.models.Registro;

import java.time.LocalDateTime;

public record RegistroResponse(
        Integer id,
        LocalDateTime dataHorario,
        TipoRegistro tipoRegistro
) {
    public RegistroResponse(Registro registro) {
        this(
                registro.getId(),
                registro.getDataHorario(),
                registro.getTipoRegistro()
        );
    }
}
