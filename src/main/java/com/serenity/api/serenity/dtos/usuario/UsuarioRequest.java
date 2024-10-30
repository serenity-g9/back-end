package com.serenity.api.serenity.dtos.usuario;

import com.serenity.api.serenity.models.embeddable.Contato;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UsuarioRequest(
        @NotBlank
        @Email
        String email,

        @NotBlank
//        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!#@$%&])(?=.*[0-9])(?=.*[a-z]).{6,15}$")
        String senha,

        @NotNull
        Integer tipoUsuario,

        @NotNull
        Contato contato
) {
}
