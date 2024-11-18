package com.serenity.api.serenity.services;

import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.Demanda;
import com.serenity.api.serenity.models.Escala;
import com.serenity.api.serenity.models.Evento;
import com.serenity.api.serenity.models.Usuario;
import com.serenity.api.serenity.repositories.DemandaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class DemandaServiceTest {

    @Mock
    private DemandaRepository demandaRepository;

    @InjectMocks
    private DemandaService demandaService;


    @Test
    @DisplayName("Nada no banco, lista vazia")
    void buscarTodos() {
        List<Demanda> lista = Collections.emptyList();
        Mockito.when(demandaRepository.findAll()).thenReturn(lista);
        List<Demanda> resultado = demandaService.listar();
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("banco com informação, retorna lista com resultados")
    void buscarTodosListaCheia() {
        List<Demanda> Demandas = List.of(
                new Demanda(
                        UUID.randomUUID(),
                        "Demanda1",
                        LocalDateTime.now(),
                        LocalDateTime.now().plusMonths(2),
                        10.00,
                        0,
                        new Evento(),
                        new Usuario(),
                        List.of(new Escala(), new Escala())
                ),
                new Demanda(
                        UUID.randomUUID(),
                        "Demanda1",
                        LocalDateTime.now(),
                        LocalDateTime.now().plusMonths(2),
                        10.00,
                        0,
                        new Evento(),
                        new Usuario(),
                        List.of(new Escala(), new Escala())
                )
        );

        Mockito.when(demandaRepository.findAll()).thenReturn(Demandas);

        List<Demanda> resultado = demandaService.listar();

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(Demandas.size(), resultado.size());
        assertArrayEquals(Demandas.toArray(), resultado.toArray());
    }


    @Test
    @DisplayName("Demanda pelo id existente, retorne corretamente")
    void buscarPorIdCorretamente() {
        UUID id = UUID.randomUUID();
        Demanda DemandaEsperado = new Demanda(
                UUID.randomUUID(),
                "Demanda1",
                LocalDateTime.now(),
                LocalDateTime.now().plusMonths(2),
                10.00,
                0,
                new Evento(),
                new Usuario(),
                List.of(new Escala(), new Escala())
        );

        Mockito.when(demandaRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(DemandaEsperado));
        Demanda resultado = demandaService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(DemandaEsperado.getId(), resultado.getId());
    }

    @Test
    @DisplayName("Id inexistente, retorna exception")
    void buscarPorIdIncorreto() {
        UUID id = UUID.randomUUID();
        Mockito.when(demandaRepository.findById(id)).thenReturn(Optional.empty());
        NaoEncontradoException ex = assertThrows(NaoEncontradoException.class, () -> demandaService.buscarPorId(id));

        assertEquals("O objeto demanda não foi encontrado(a)", ex.getMessage());
    }
}