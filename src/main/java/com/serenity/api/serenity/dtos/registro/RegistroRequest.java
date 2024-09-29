package com.serenity.api.serenity.dtos.registro;

import com.serenity.api.serenity.enums.TipoRegistro;
import jakarta.validation.constraints.NotBlank;

public record RegistroRequest(
        @NotBlank
        Integer idAgendamento,
        @NotBlank
        TipoRegistro tipoRegistro
) {
}
