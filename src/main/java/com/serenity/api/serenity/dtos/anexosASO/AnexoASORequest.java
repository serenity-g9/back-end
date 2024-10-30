package com.serenity.api.serenity.dtos.anexosASO;

import com.serenity.api.serenity.models.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.UUID;

public record AnexoASORequest(
        @PastOrPresent
        LocalDate dataEmitido,
        @NotBlank
        String urlAnexo,
        @NotNull
        UUID idUsuario
) {
}
