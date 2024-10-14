package com.serenity.api.serenity.dtos.evento;

import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import com.serenity.api.serenity.models.Evento;
import com.serenity.api.serenity.models.Imagem;
import com.serenity.api.serenity.models.embeddable.Endereco;

import java.time.LocalDate;
import java.util.UUID;

public record EventoResponse(
        UUID id,
        String nome,
        Double orcamento,
        LocalDate inicio,
        LocalDate fim,
        String logradouro,
        String numero,
        String cep,
        String cidade,
        String uf,
        Imagem imagem
) {
    public EventoResponse(Evento evento) {
        this(
                evento.getId(),
                evento.getNome(),
                evento.getOrcamento(),
                evento.getInicio(),
                evento.getFim(),
                evento.getEndereco().getLogradouro(),
                evento.getEndereco().getNumero(),
                evento.getEndereco().getCep(),
                evento.getEndereco().getCidade(),
                evento.getEndereco().getUf(),
                evento.getImagem()
        );
    }
}
