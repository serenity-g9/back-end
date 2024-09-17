package com.serenity.api.serenity.dtos.formulario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.serenity.api.serenity.interfaces.Ordenavel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class Questao implements Ordenavel {
    private String idQuestao;
    private String titulo;

    @Override
    public Comparable<String> getValorOrdenacao() {
        return titulo;
    }

    public Questao(String idQuestao, String titulo) {
        this.idQuestao = idQuestao;
        this.titulo = titulo;
    }
}
