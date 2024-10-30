package com.serenity.api.serenity.dtos.registro;

import com.serenity.api.serenity.enums.TipoRegistro;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record RegistroRequest(
        @NotBlank
        UUID idAgendamento,
        @NotBlank
        TipoRegistro tipoRegistro
) {
}
