package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.comissao.ComissaoRequest;
import com.serenity.api.serenity.dtos.comissao.ComissaoResponse;
import com.serenity.api.serenity.dtos.comissao.ComissaoUpdateRequest;
import com.serenity.api.serenity.mappers.ComissaoMapper;
import com.serenity.api.serenity.services.ComissaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/comissoes")
@RequiredArgsConstructor
public class ComissaoController {
    private final ComissaoService comissaoService;
    private final ComissaoMapper mapper;

    @GetMapping
    public ResponseEntity<List<ComissaoResponse>> buscar() {
        List<ComissaoResponse> agendamentoResponses = comissaoService.listar().stream()
                .map(ComissaoResponse::new)
                .collect(Collectors.toList());

        return ok(agendamentoResponses);
    }

    @PostMapping
    public ResponseEntity<ComissaoResponse> cadastrar(@RequestBody @Valid ComissaoRequest comissaoRequest) {
        return created(null).body(new ComissaoResponse(comissaoService.cadastrar(mapper.toComissao(comissaoRequest))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComissaoResponse> buscarPorId(@PathVariable UUID id) {
        return ok(new ComissaoResponse(comissaoService.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComissaoResponse> atualizar(@PathVariable UUID id, @RequestBody @Valid ComissaoUpdateRequest comissaoUpdateRequest) {
        return ok(new ComissaoResponse(comissaoService.atualizar(id, mapper.toComissao(comissaoUpdateRequest, comissaoService.buscarPorId(id)))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (@PathVariable UUID id) {
        comissaoService.deletar(id);
        return noContent().build();
    }
}