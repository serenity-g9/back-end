package com.serenity.api.serenity.dtos.formulario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.serenity.api.serenity.interfaces.Ordenavel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class Questao implements Ordenavel {
    private String id;
    private String titulo;
    private String tipo;

    @Override
    public Comparable<String> getValorOrdenacao() {
        return titulo;
    }

    public Questao(String id, String titulo, String tipo) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
    }
}
