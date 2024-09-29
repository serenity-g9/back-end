package com.serenity.api.serenity.models.embeddable;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Embeddable
@Getter
@Setter
public class Contato {
    private String nome;
    private String celular;
    private LocalDate dataNascimento;
}
