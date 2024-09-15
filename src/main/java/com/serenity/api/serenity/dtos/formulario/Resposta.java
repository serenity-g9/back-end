package com.serenity.api.serenity.dtos.formulario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.serenity.api.serenity.interfaces.Ordenavel;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@JsonIgnoreProperties
public class Resposta {
    private String idQuestao;
    private Set<String> valores;

    public Resposta(String idQuestao, Set<String> valores) {
        this.idQuestao = idQuestao;
        this.valores = valores;
    }
}
