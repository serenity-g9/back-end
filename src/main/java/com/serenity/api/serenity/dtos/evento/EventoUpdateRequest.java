package com.serenity.api.serenity.dtos.evento;

import com.serenity.api.serenity.models.embeddable.Endereco;
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
        LocalDateTime fim,

        @NotNull
        Endereco endereco
){

}
