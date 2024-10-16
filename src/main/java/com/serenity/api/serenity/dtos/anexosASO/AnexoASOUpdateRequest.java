package com.serenity.api.serenity.dtos.anexosASO;

import com.serenity.api.serenity.models.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record AnexoASOUpdateRequest(
        @PastOrPresent
        LocalDate dataEmitido,
        @NotBlank
        String urlAnexo
){
}
