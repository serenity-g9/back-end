package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.models.formulario.Questao;
import com.serenity.api.serenity.models.formulario.RespostaUsuario;
import com.serenity.api.serenity.services.FormularioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/forms")
public class FormularioController {

    @GetMapping("/respostas/{id}")
    public ResponseEntity<List<RespostaUsuario>> getRespostas(@PathVariable String id) {
        return ResponseEntity.status(200).body(FormularioService.getRespostas(id));
    }

    @GetMapping("/questoes/{id}")
    public ResponseEntity<List<Questao>> getQuestoes(@PathVariable String id) {
        return ResponseEntity.status(200).body(FormularioService.getQuestoes(id));
    }
}
