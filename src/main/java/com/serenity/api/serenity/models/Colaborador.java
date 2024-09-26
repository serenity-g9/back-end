package com.serenity.api.serenity.models;

import com.serenity.api.serenity.enums.FuncaoInteresse;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<FuncaoInteresse> funcoesInteresse;

    private String cidadeResidencia;
    private String estado;
    private String apresentacao;
    private String experiencia;
    private String linkSocial;
    private Boolean sociaisAbertas;

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
