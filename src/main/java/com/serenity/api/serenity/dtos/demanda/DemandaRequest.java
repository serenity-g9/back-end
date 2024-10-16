package com.serenity.api.serenity.dtos.demanda;

import com.serenity.api.serenity.models.Evento;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record DemandaRequest (

        @NotBlank
        String nome,
        @NotNull
        Double orcamento,
        @Future
        LocalDate inicio,
        @Future
        LocalDate fim,
        @NotNull
        Double custoTotal,
        @NotNull
        Integer tipoContrato,
        Evento evento
        ){
}
