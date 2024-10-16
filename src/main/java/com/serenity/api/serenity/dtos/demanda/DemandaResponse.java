package com.serenity.api.serenity.dtos.demanda;

import com.serenity.api.serenity.models.Demanda;
import com.serenity.api.serenity.services.DemandaService;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record DemandaResponse(
        @NotNull
        UUID id,
        @NotBlank
        String nome,
        @NotNull
        Double orcamento,
        @Future
        LocalDate inicio,
        @Future
        LocalDate fim
) {
    public DemandaResponse(Demanda demanda) {
        this(
            demanda.getId(),
            demanda.getNome(),
            demanda.getCustoTotal(),
            demanda.getInicio(),
            demanda.getFim()
        );
    }
}
