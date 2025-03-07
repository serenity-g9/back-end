package com.serenity.api.serenity.dtos.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record InfoPatchRequest( String novoNome, LocalDate dataNascimento, String celular) {
}
