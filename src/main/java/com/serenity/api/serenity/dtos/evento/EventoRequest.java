package com.serenity.api.serenity.dtos.evento;

import com.serenity.api.serenity.models.embeddable.Endereco;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record EventoRequest(
        @NotBlank
        @Size(min = 3)
        String nome,

        @NotNull
        Double orcamento,

        @FutureOrPresent
        LocalDate inicio,

        @Future
        LocalDate fim,

        @NotNull
        Endereco endereco
){

}
