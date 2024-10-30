package com.serenity.api.serenity.dtos.autenticacao;

import com.serenity.api.serenity.models.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record LoginRequest(
        @Email
        String email,
//        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!#@$%&])(?=.*[0-9])(?=.*[a-z]).{6,15}$")
        String senha
) {
    public LoginRequest(Usuario usuario) {
        this(
                usuario.getEmail(),
                usuario.getSenha()
        );
    }
}
