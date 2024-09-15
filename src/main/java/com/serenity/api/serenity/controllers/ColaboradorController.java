package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.models.Colaborador;
import com.serenity.api.serenity.services.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {
    @Autowired
    private ColaboradorService colaboradorService;

    @GetMapping
    public ResponseEntity<List<Colaborador>> buscar() {
        List<Colaborador> colaboradores = colaboradorService.listar();
        return colaboradores.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(colaboradores);
    }

    @PostMapping
    public ResponseEntity<Colaborador> cadastrar(@RequestBody Colaborador colaborador) {
        return ResponseEntity.status(201).body(colaboradorService.cadastrar(colaborador));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colaborador> buscarPorId(@PathVariable int id){
        return ResponseEntity.status(200).body(colaboradorService.buscarPorId(id).get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Colaborador> atualizar(@PathVariable int id, @RequestBody Colaborador colaboradorAtualizado) {
        return  ResponseEntity.status(200).body(colaboradorService.atualizar(id, colaboradorAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Colaborador> deletar (@PathVariable int id) {
        colaboradorService.deletar(id);
        return ResponseEntity.status(204).build();
    }

//    @PatchMapping("/{id}/comissao")
//    public ResponseEntity<Colaborador> cadastrarComissao(@RequestBody Comissao comissao, @PathVariable int id) {
//        return ResponseEntity.status(201).body(colaboradorService.cadastrarComissao(comissao, id));
//    }
//
//    @GetMapping("/{id}/faturamento")
//    public ResponseEntity<List<Faturamento>> buscarFaturavel(@PathVariable int id) {
//        List<Faturamento> faturamentos = colaboradorService.buscarFaturaveis(id);
//        return faturamentos.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(faturamentos);
//    }
}
