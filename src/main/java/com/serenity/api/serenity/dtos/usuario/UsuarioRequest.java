package com.serenity.api.serenity.dtos.usuario;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UsuarioRequest(
        @NotBlank
        @Email
        String email,
        @NotBlank
//        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!#@$%&])(?=.*[0-9])(?=.*[a-z]).{6,15}$")
        String senha,
        @NotBlank
        @Size(min = 3)
        String nome,
        @NotBlank
        String celular,
        @PastOrPresent
        LocalDate dataNascimento
) {
}
