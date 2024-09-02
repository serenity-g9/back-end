package com.serenity.api.serenity.models.formulario;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.serenity.api.serenity.interfaces.Ordenavel;

import java.time.LocalDateTime;
import java.util.List;

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

    public String getIdResposta() {
        return idResposta;
    }

    public void setIdResposta(String idResposta) {
        this.idResposta = idResposta;
    }

    public LocalDateTime getHorarioEnviado() {
        return horarioEnviado;
    }

    public void setHorarioEnviado(LocalDateTime horarioEnviado) {
        this.horarioEnviado = horarioEnviado;
    }

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }
}
