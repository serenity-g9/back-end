package com.serenity.api.serenity.dtos.formulario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@JsonIgnoreProperties
public class RespostaQuestao {
    private String id;
    private Set<String> valor;
    private LocalDateTime horarioEnviado;

    public RespostaQuestao(String id, Set<String> valor, LocalDateTime horarioEnviado) {
        this.id = id;
        this.valor = valor;
        this.horarioEnviado = horarioEnviado;
    }
}
