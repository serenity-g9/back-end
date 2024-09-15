package com.serenity.api.serenity.dtos.usuario;

import com.serenity.api.serenity.models.Usuario;

import java.time.LocalDate;

public record UsuarioResponse(
        Integer id,
        String nome,
        String email,
        String celular,
        LocalDate dataNascimento
) {
    public UsuarioResponse(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCelular(),
                usuario.getDataNascimento()
        );
    }
}
