package com.serenity.api.serenity.models.formulario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.serenity.api.serenity.interfaces.Ordenavel;

import java.util.Set;

@JsonIgnoreProperties
public class Resposta implements Ordenavel {
    private String idQuestao;
    private Set<String> valores;

    public Resposta(String idQuestao, Set<String> valores) {
        this.idQuestao = idQuestao;
        this.valores = valores;
    }

    public String getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(String idQuestao) {
        this.idQuestao = idQuestao;
    }

    public Set<String> getValores() {
        return valores;
    }

    public void setValores(Set<String> valores) {
        this.valores = valores;
    }

    @Override
    public Comparable getValorOrdenacao() {
        return null;
    }
}
