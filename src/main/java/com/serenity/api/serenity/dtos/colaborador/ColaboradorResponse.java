package com.serenity.api.serenity.dtos.colaborador;

import com.serenity.api.serenity.enums.FuncaoInteresse;
import com.serenity.api.serenity.models.Colaborador;

import java.time.LocalDate;
import java.util.Set;

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
    public ColaboradorResponse(Colaborador colaborador) {
        this(
                colaborador.getId(),
                colaborador.getContato().getNome(),
                colaborador.getUsuario().getEmail(),
                colaborador.getContato().getCelular(),
                colaborador.getContato().getDataNascimento(),
                colaborador.getFuncoesInteresse(),
                colaborador.getCidadeResidencia(),
                colaborador.getEstado(),
                colaborador.getLinkSocial(),
                colaborador.getSociaisAbertas(),
                colaborador.getApresentacao(),
                colaborador.getExperiencia()
        );
    }
}