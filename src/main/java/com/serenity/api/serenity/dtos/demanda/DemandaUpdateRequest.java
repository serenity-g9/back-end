package com.serenity.api.serenity.dtos.demanda;

import com.serenity.api.serenity.models.Evento;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DemandaUpdateRequest(

        @NotBlank
        String nome,
        @NotNull
        Double orcamento,
        @FutureOrPresent
        LocalDate inicio,
        @FutureOrPresent
        LocalDate fim,
        @NotNull
        Double custoTotal,
        @NotNull
        Integer tipoContrato,
        Evento evento) {
}
