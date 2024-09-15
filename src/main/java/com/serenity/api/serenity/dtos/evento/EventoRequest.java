package com.serenity.api.serenity.dtos.evento;

import java.time.LocalDate;

public record EventoRequest(
        String nome,
        Double orcamento,
        LocalDate inicio,
        LocalDate fim
){

}
