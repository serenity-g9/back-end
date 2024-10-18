package com.serenity.api.serenity.dtos.anexosASO;

import com.serenity.api.serenity.models.AnexoASO;
import com.serenity.api.serenity.models.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.UUID;

public record AnexoASOResponse(
        @NotNull
        UUID id,
        @PastOrPresent
        LocalDate dataEmitido,
        @NotBlank
        String urlAnexo,
        @NotNull
        Usuario usuario
)
{
    public AnexoASOResponse(AnexoASO anexoASO){
        this(
        anexoASO.getId(),
        anexoASO.getDataEmitido(),
        anexoASO.getUrlAnexo(),
        anexoASO.getUsuario()
        );
    }
}

