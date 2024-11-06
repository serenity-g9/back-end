package com.serenity.api.serenity.models;

import com.serenity.api.serenity.models.embeddable.Endereco;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evento extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;
    private Double orcamento;
    private LocalDateTime inicio;
    private LocalDateTime fim;

    @ManyToOne
    private Usuario responsavel;

    @OneToOne(cascade = CascadeType.ALL)
    private Anexo imagem;

    @ManyToOne
    private Formulario formulario;

    @Embedded
    private Endereco endereco;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evento")
    private List<Demanda> demandas;
}
