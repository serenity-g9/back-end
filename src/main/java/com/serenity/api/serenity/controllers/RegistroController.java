package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.registro.RegistroRequest;
import com.serenity.api.serenity.dtos.registro.RegistroResponse;
import com.serenity.api.serenity.mappers.RegistroMapper;
import com.serenity.api.serenity.services.RegistroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/registros")
@RequiredArgsConstructor
public class RegistroController {

    private final RegistroService registroService;
    private final RegistroMapper mapper;

    @GetMapping
    public ResponseEntity<List<RegistroResponse>> buscar() {
        List<RegistroResponse> agendamentoResponses = registroService.listar().stream()
                .map(RegistroResponse::new)
                .collect(Collectors.toList());

        return ok(agendamentoResponses);
    }

    @PostMapping
    public ResponseEntity<RegistroResponse> cadastrar(@RequestBody @Valid RegistroRequest registroRequest) {
        return created(null).body(new RegistroResponse(registroService.cadastrar(mapper.toRegistro(registroRequest))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroResponse> buscarPorId(@PathVariable Integer id) {
        return ok(new RegistroResponse(registroService.buscarPorId(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        registroService.deletar(id);
        return noContent().build();
    }
}
