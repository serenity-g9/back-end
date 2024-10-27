package com.serenity.api.serenity.dtos.formulario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record FormularioRequest(
        @NotBlank
        @Size(min = 3)
        String nome,

        @NotBlank
        @URL
        String url
) {
}
