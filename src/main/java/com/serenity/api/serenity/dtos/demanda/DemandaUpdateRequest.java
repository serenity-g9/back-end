package com.serenity.api.serenity.dtos.demanda;

import com.serenity.api.serenity.models.Evento;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record   DemandaUpdateRequest(

        @NotBlank
        String nome,
        @FutureOrPresent
        LocalDateTime inicio,
        @FutureOrPresent
        LocalDateTime fim,
        @NotNull
        Double custoTotal) {
}
