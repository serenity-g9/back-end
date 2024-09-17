package com.serenity.api.serenity.dtos.formulario;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.serenity.api.serenity.interfaces.Ordenavel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties
public class RespostaUsuario implements Ordenavel<LocalDateTime> {
    private String idResposta;
    private LocalDateTime horarioEnviado;
    private List<Resposta> respostas;

    @Override
    public LocalDateTime getValorOrdenacao() {
        return this.horarioEnviado;
    }

    public RespostaUsuario(String idResposta, LocalDateTime horarioEnviado, List<Resposta> respostas) {
        this.idResposta = idResposta;
        this.horarioEnviado = horarioEnviado;
        this.respostas = respostas;
    }
}
