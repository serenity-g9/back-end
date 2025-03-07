package com.serenity.api.serenity.dtos.formulario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class GoogleForm {
    private String id;
    private String nome;

    public GoogleForm(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
