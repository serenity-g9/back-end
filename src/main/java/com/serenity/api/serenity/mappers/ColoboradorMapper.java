package com.serenity.api.serenity.mappers;

import com.serenity.api.serenity.dtos.colaborador.ColaboradorRequest;
import com.serenity.api.serenity.dtos.colaborador.ColaboradorResponse;
import com.serenity.api.serenity.dtos.colaborador.ColaboradorUpdateRequest;
import com.serenity.api.serenity.enums.FuncaoInteresse;
import com.serenity.api.serenity.models.Colaborador;
import com.serenity.api.serenity.models.Usuario;

import java.time.LocalDate;
import java.util.Set;

public class ColoboradorMapper {
    public static ColaboradorRequest userToColaboradorRequest(Colaborador colaborador){

        return ColaboradorRequest.builder()
                .idUsuario(colaborador.getUsuario().getId())
                .funcoesInteresse(colaborador.getFuncoesInteresse())
                .cidadeResidencia(colaborador.getCidadeResidencia())
                .estado(colaborador.getEstado())
                .apresentacao(colaborador.getApresentacao())
                .experiencia(colaborador.getExperiencia())
                .linkSocial(colaborador.getLinkSocial())
                .sociaisAbertas(colaborador.getSociaisAbertas())
                .build();
    }

    public static ColaboradorResponse userToColaboradorResponse(Colaborador colaborador){
        return ColaboradorResponse.builder()
                .id(colaborador.getId())
                .nome(colaborador.getUsuario().getNome())
                .email(colaborador.getUsuario().getEmail())
                .celular(colaborador.getUsuario().getCelular())
                .dataNascimento(colaborador.getUsuario().getDataNascimento())
                .funcoesInteresse(colaborador.getFuncoesInteresse())
                .cidadeResidencia(colaborador.getCidadeResidencia())
                .estado(colaborador.getEstado())
                .linkSocial(colaborador.getLinkSocial())
                .sociaisAbertas(colaborador.getSociaisAbertas())
                .apresentacao(colaborador.getApresentacao())
                .experiencia(colaborador.getExperiencia())
                .build();
    }

    public static ColaboradorUpdateRequest userToColaboradorUpdateRequest(Colaborador colaborador){
        return ColaboradorUpdateRequest.builder()
                .funcoesInteresse(colaborador.getFuncoesInteresse())
                .cidadeResidencia(colaborador.getCidadeResidencia())
                .estado(colaborador.getEstado())
                .apresentacao(colaborador.getApresentacao())
                .experiencia(colaborador.getExperiencia())
                .linkSocial(colaborador.getLinkSocial())
                .sociaisAbertas(colaborador.getSociaisAbertas())
                .build();
    }
}
