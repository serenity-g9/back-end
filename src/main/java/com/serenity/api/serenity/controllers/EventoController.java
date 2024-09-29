package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.evento.EventoRequest;
import com.serenity.api.serenity.dtos.evento.EventoResponse;
import com.serenity.api.serenity.dtos.evento.EventoUpdateRequest;
import com.serenity.api.serenity.mappers.EventoMapper;
import com.serenity.api.serenity.services.EventoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventoService;
    private final EventoMapper mapper;

    @GetMapping
    public ResponseEntity<List<EventoResponse>> buscar() {
        List<EventoResponse> agendamentoResponses = eventoService.listar().stream()
                .map(EventoResponse::new)
                .collect(Collectors.toList());

        return ok(agendamentoResponses);
    }

    @PostMapping
    public ResponseEntity<EventoResponse> cadastrar(@RequestBody @Valid EventoRequest eventoRequest) {
        return created(null).body(new EventoResponse(eventoService.cadastrar(mapper.toEvento(eventoRequest))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoResponse> buscarPorId(@PathVariable Integer id) {
        return ok(new EventoResponse(eventoService.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoResponse> atualizar(@PathVariable Integer id, @RequestBody @Valid EventoUpdateRequest eventoUpdateRequest) {
        return ok(new EventoResponse(eventoService.atualizar(id, mapper.toEvento(eventoUpdateRequest, eventoService.buscarPorId(id)))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        eventoService.deletar(id);
        return noContent().build();
    }
}
