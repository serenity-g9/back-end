package com.serenity.api.serenity.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String regiaoResidencia;
    private String intermitente;
    private Boolean ASO;

    @OneToOne
    private Usuario usuario;

//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Faturamento> faturamentos = new ArrayList<>();
//
//    public Double getFaturamento() {
//        Double total = 0.0;
//
//        for (Faturamento faturamento : faturamentos) {
//            total += faturamento.getFaturamento();
//        }
//
//        return total;
//    }
//
//    public void addFaturamento(Faturamento faturamento) {
//        faturamentos.add(faturamento);
//    }
}
