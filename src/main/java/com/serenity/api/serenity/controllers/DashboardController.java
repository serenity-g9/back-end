package com.serenity.api.serenity.controllers;

import com.serenity.api.serenity.dtos.dashboard.DashboardResponse;
import com.serenity.api.serenity.services.DashboardService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping(value = "/dashboard", produces = {"application/json"})
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardResponse> buscarDados(
            @RequestParam UUID idUsuario,
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim) {

        return ResponseEntity.ok(dashboardService.buscarDados(idUsuario, inicio, fim));
    }

}
