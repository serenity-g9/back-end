package com.serenity.api.serenity.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("COMISSAO")
public class Comissao extends Faturamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer idColaborador;
    private Integer idEvento;
    private Double valor;
    private int quantidade;

    @Override
    public Double getFaturamento() {
        return quantidade * valor;
    }
}
