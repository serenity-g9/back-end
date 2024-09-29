package com.serenity.api.serenity.dtos.usuario;

import java.time.LocalDate;

public record UsuarioRequest(
        String email,
        String senha,
        String nome,
        String celular,
        LocalDate dataNascimento
) {
}
