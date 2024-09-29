package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.parceiro.ParceiroRequest;
import com.serenity.api.serenity.dtos.parceiro.ParceiroResponse;
import com.serenity.api.serenity.dtos.parceiro.ParceiroUpdateRequest;
import com.serenity.api.serenity.mappers.ParceiroMapper;
import com.serenity.api.serenity.services.ParceiroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/parceiros")
@RequiredArgsConstructor
public class ParceiroController {

    private final ParceiroService parceiroService;
    private final ParceiroMapper mapper;

    @GetMapping
    public ResponseEntity<List<ParceiroResponse>> buscar() {
        List<ParceiroResponse> agendamentoResponses = parceiroService.listar().stream()
                .map(ParceiroResponse::new)
                .collect(Collectors.toList());

        return ok(agendamentoResponses);
    }

    @PostMapping
    public ResponseEntity<ParceiroResponse> cadastrar(@RequestBody @Valid ParceiroRequest parceiroRequest) {
        return created(null).body(new ParceiroResponse(parceiroService.cadastrar(mapper.toParceiro(parceiroRequest))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParceiroResponse> buscarPorId(@PathVariable Integer id) {
        System.out.println(parceiroService.buscarPorId(id));
        return ok(new ParceiroResponse(parceiroService.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParceiroResponse> atualizar(@PathVariable Integer id, @RequestBody @Valid ParceiroUpdateRequest parceiroUpdateRequest) {
        return ok(new ParceiroResponse(parceiroService.atualizar(id, mapper.toParceiro(parceiroUpdateRequest, parceiroService.buscarPorId(id)))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        parceiroService.deletar(id);
        return noContent().build();
    }
}
