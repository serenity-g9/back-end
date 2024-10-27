package com.serenity.api.serenity.dtos.evento;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record EventoUpdateRequest(
        @NotBlank
        @Size(min = 3)
        String nome,
        @NotNull
        Double orcamento,
//        @FutureOrPresent
        LocalDateTime inicio,
//        @Future
        LocalDateTime fim
){

}
