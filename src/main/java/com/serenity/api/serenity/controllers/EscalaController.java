package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.escala.EscalaRequest;
import com.serenity.api.serenity.dtos.escala.EscalaResponse;
import com.serenity.api.serenity.dtos.escala.EscalaUpdateRequest;
import com.serenity.api.serenity.mappers.EscalaMapper;
import com.serenity.api.serenity.services.EscalaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/escalas")
@RequiredArgsConstructor
public class EscalaController {

    private final EscalaService escalaService;
    private final EscalaMapper mapper;

    @GetMapping
    public ResponseEntity<List<EscalaResponse>> buscar() {
        List<EscalaResponse> agendamentoResponses = escalaService.listar().stream()
                .map(EscalaResponse::new)
                .collect(Collectors.toList());

        return ok(agendamentoResponses);
    }

    @PostMapping
    public ResponseEntity<EscalaResponse> cadastrar(@RequestBody @Valid EscalaRequest escalaRequest) {
        return created(null).body(new EscalaResponse(escalaService.cadastrar(mapper.toEscala(escalaRequest))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EscalaResponse> buscarPorId(@PathVariable UUID id) {
        return ok(new EscalaResponse(escalaService.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EscalaResponse> atualizar(@PathVariable UUID id, @RequestBody @Valid EscalaUpdateRequest escalaUpdateRequest) {
        return ok(new EscalaResponse(escalaService.atualizar(id, mapper.toEscala(escalaUpdateRequest, escalaService.buscarPorId(id)))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        escalaService.deletar(id);
        return noContent().build();
    }
}
