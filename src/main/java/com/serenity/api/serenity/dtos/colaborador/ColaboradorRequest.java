package com.serenity.api.serenity.dtos.colaborador;

import com.serenity.api.serenity.enums.FuncaoInteresse;
import com.serenity.api.serenity.models.embeddable.Contato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.Set;
@Builder
public record ColaboradorRequest(
        @NotNull
        Integer idUsuario,
        Set<FuncaoInteresse> funcoesInteresse,
        @NotBlank
        String cidadeResidencia,
        @NotBlank
        String estado,
        @NotBlank
        String apresentacao,
        @NotNull
        String experiencia,
        @NotNull
        String linkSocial,
        Boolean sociaisAbertas,
        Contato contato
) {

}
