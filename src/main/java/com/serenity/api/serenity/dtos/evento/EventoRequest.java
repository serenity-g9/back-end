package com.serenity.api.serenity.dtos.evento;

import com.serenity.api.serenity.models.embeddable.Endereco;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record EventoRequest(
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
        Endereco endereco,

        UUID idFormulario,
        UUID idResponsavel
){

}
