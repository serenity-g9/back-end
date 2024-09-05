package com.serenity.api.serenity.models.formulario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.serenity.api.serenity.interfaces.Ordenavel;

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

    public String getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(String idQuestao) {
        this.idQuestao = idQuestao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
