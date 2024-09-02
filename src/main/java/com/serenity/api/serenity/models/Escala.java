package com.serenity.api.serenity.models;

import com.serenity.api.serenity.enums.FuncaoAlocacao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Escala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int idColaborador;
    private int idEvento;
    private LocalDateTime dataHorario;
    private int funcaoAlocada;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(int idColaborador) {
        this.idColaborador = idColaborador;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public LocalDateTime getDataHorario() {
        return dataHorario;
    }

    public void setDataHorario(LocalDateTime dataHorario) {
        this.dataHorario = dataHorario;
    }

    public String getFuncaoAlocada() {
        return FuncaoAlocacao.getValorById(funcaoAlocada);
    }

    public void setFuncaoAlocada(int funcaoAlocada) {
        this.funcaoAlocada = funcaoAlocada;
    }
}
