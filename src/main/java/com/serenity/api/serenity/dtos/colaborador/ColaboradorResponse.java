package com.serenity.api.serenity.dtos.colaborador;

import com.serenity.api.serenity.enums.FuncaoInteresse;
import com.serenity.api.serenity.models.Colaborador;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;

@Builder
public record ColaboradorResponse(
    Integer id,
    String nome,
    String email,
    String celular,
    LocalDate dataNascimento,
    Set<FuncaoInteresse> funcoesInteresse,
    String cidadeResidencia,
    String estado,
    String linkSocial,
    Boolean sociaisAbertas,
    String apresentacao,
    String experiencia
) {
}
