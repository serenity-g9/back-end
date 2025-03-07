package com.serenity.api.serenity.dtos.formulario;

import java.util.List;
import java.util.Map;

public record FormsResponse(
        Map<String, QuestaoResponse> forms
) {

}

record QuestaoResponse(
   Questao questao,
   List<Resposta> respostas
) {}
