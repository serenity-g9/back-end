package com.serenity.api.serenity.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String regiaoResidencia;
    private Integer idUsuario;
    private String intermitente;
    private Boolean ASO;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Faturamento> faturamentos = new ArrayList<>();

    public Double getFaturamento() {
        Double total = 0.0;

        for (Faturamento faturamento : faturamentos) {
            total += faturamento.getFaturamento();
        }

        return total;
    }

    public void addFaturamento(Faturamento faturamento) {
        faturamentos.add(faturamento);
    }
}
