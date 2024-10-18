package com.serenity.api.serenity.dtos.demanda;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

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
        @NotNull
        UUID idEvento
        ) {
}
