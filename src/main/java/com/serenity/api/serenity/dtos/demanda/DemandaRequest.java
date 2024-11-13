package com.serenity.api.serenity.dtos.demanda;

import com.serenity.api.serenity.dtos.escala.EscalaBatchRequest;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record DemandaRequest (
        @NotBlank
        String nome,

        @Future
        LocalDateTime inicio,

        @Future
        LocalDateTime fim,

        @NotNull
        Double custoTotal,

        @NotNull
        Integer tipoContrato,

        List<EscalaBatchRequest> escalas,

        @NotNull
        UUID idEvento,

        @NotNull
        UUID idResponsavel
        ) {
}
