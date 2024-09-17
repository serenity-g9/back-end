package com.serenity.api.serenity.dtos.registro;

import com.serenity.api.serenity.enums.TipoRegistro;

public record RegistroRequest(
        Integer idAgendamento,
        TipoRegistro tipoRegistro
) {
}
