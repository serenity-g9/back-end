package com.serenity.api.serenity.models;

import com.serenity.api.serenity.interfaces.Faturavel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_faturamento")
public abstract class Faturamento implements Faturavel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
