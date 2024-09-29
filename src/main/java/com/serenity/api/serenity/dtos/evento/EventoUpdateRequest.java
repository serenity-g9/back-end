package com.serenity.api.serenity.dtos.evento;

import java.time.LocalDate;

public record EventoUpdateRequest(
        String nome,
        Double orcamento,
        LocalDate inicio,
        LocalDate fim
){

}
