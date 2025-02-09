package com.serenity.api.serenity.dtos.formulario;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties
public class QuestaoRespostas {
    private Questao questao;
    private List<RespostaQuestao> respostas;

    public QuestaoRespostas(Questao questao, List<RespostaQuestao> respostas) {
        this.questao = questao;
        this.respostas = respostas;
    }
}

