package com.serenity.api.serenity.models;

import com.serenity.api.serenity.models.embeddable.Endereco;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evento implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;
    private Double orcamento;
    private LocalDate inicio;
    private LocalDate fim;

    @OneToOne(cascade = CascadeType.ALL)
    private Imagem imagem;

    @Embedded
    private Endereco endereco;
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s",
                id,
                nome,
                orcamento,
                inicio,
                fim,
                imagem != null ? imagem.getUrl() : "",
                endereco != null ? endereco.toString() : ""
        );
    }

}
