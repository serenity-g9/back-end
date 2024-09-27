package com.serenity.api.serenity.dtos.colaborador;

import com.serenity.api.serenity.enums.FuncaoInteresse;
import lombok.Builder;

import java.util.Set;
@Builder
public record ColaboradorUpdateRequest(
        Set<FuncaoInteresse> funcoesInteresse,
        String cidadeResidencia,
        String estado,
        String apresentacao,
        String experiencia,
        String linkSocial,
        Boolean sociaisAbertas
) {
}
