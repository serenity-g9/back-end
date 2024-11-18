package com.serenity.api.serenity.dtos.usuario;

import com.serenity.api.serenity.models.embeddable.Contato;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UsuarioUpdateRequest(
        @Email
        @NotBlank
        String email,
//        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!#@$%&])(?=.*[0-9])(?=.*[a-z]).{6,15}$")
        Contato contato
) {
}
