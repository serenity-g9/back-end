package com.serenity.api.serenity.models;

import com.serenity.api.serenity.interfaces.IProfissional;
import com.serenity.api.serenity.services.RegistroService;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Getter
@Setter
@Entity
public class Parceiro implements IProfissional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer idUsuario;

    @Autowired
    private RegistroService registroService;

    @Override
    public List<Registro> getRegistros() {
        return registroService.buscarRegistros();
    }
}
