package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.colaborador.ColaboradorRequest;
import com.serenity.api.serenity.dtos.colaborador.ColaboradorResponse;
import com.serenity.api.serenity.dtos.colaborador.ColaboradorUpdateRequest;
import com.serenity.api.serenity.mappers.ColaboradorMapper;
import com.serenity.api.serenity.services.ColaboradorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/colaboradores")
@RequiredArgsConstructor
public class ColaboradorController {

    private final ColaboradorService colaboradorService;
    private final ColaboradorMapper mapper;

    @GetMapping
    public ResponseEntity<List<ColaboradorResponse>> buscar() {
        List<ColaboradorResponse> agendamentoResponses = colaboradorService.listar().stream()
                .map(ColaboradorResponse::new)
                .collect(Collectors.toList());

        return ok(agendamentoResponses);
    }

    @PostMapping
    public ResponseEntity<ColaboradorResponse> cadastrar(@RequestBody @Valid ColaboradorRequest colaboradorRequest) {
        return created(null).body(new ColaboradorResponse(colaboradorService.cadastrar(mapper.toColaborador(colaboradorRequest))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColaboradorResponse> buscarPorId(@PathVariable UUID id) {
        System.out.println(colaboradorService.buscarPorId(id));
        return ok(new ColaboradorResponse(colaboradorService.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColaboradorResponse> atualizar(@PathVariable UUID id, @RequestBody @Valid ColaboradorUpdateRequest colaboradorUpdateRequest) {
        return ok(new ColaboradorResponse(colaboradorService.atualizar(id, mapper.toColaborador(colaboradorUpdateRequest, colaboradorService.buscarPorId(id)))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        colaboradorService.deletar(id);
        return noContent().build();
    }
}
