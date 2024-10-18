package com.serenity.api.serenity.models.embeddable;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Embeddable
@Getter
@Setter
public class Endereco {
    private String logradouro;
    private String cep;
    private String numero;
    private String uf;
    private String cidade;
}
