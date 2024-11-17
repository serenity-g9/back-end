package com.serenity.api.serenity.services;

import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.*;
import com.serenity.api.serenity.models.embeddable.Endereco;
import com.serenity.api.serenity.repositories.EventoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class EventoServiceTest {
    @Mock
    private EventoRepository eventoRepository;

    @InjectMocks
    private EventoService eventoService;


    @Test
    @DisplayName("Nada no banco, lista vazia")
    void buscarTodos() {
        List<Evento> lista = Collections.emptyList();
        Mockito.when(eventoRepository.findAll()).thenReturn(lista);
        List<Evento> resultado = eventoService.listar();
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("banco com informação, retorna lista com resultados")
    void buscarTodosListaCheia() {
        List<Evento> eventos = List.of(
                new Evento(
                        UUID.randomUUID(),
                        "Show AM",
                        10.00,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusMonths(2),
                        new Usuario(),
                        new Anexo(),
                        new Formulario(),
                        new Endereco(),
                        List.of(new Demanda(), new Demanda())
                ),
                new Evento(

                        UUID.randomUUID(),
                        "Show LinkPark",
                        10.00,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusMonths(2),
                        new Usuario(),
                        new Anexo(),
                        new Formulario(),
                        new Endereco(),
                        List.of(new Demanda(), new Demanda())
                )
        );

        Mockito.when(eventoRepository.findAll()).thenReturn(eventos);

        List<Evento> resultado = eventoService.listar();

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(eventos.size(), resultado.size());
        assertArrayEquals(eventos.toArray(), resultado.toArray());
    }


    @Test
    @DisplayName("Evento pelo id existente, retorne corretamente")
    void buscarPorIdCorretamente() {
        UUID id = UUID.randomUUID();
        Evento eventoEsperado = new Evento
                (UUID.randomUUID(),
                        "Show AM",
                10.00,
                LocalDateTime.now(),
                LocalDateTime.now().plusMonths(2),
                new Usuario(),
                new Anexo(),
                new Formulario(),
                new Endereco(),
                List.of(new Demanda(), new Demanda())
                );
        Mockito.when(eventoRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(eventoEsperado));
        Evento resultado = eventoService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(eventoEsperado.getId(), resultado.getId());
    }

    @Test
    @DisplayName("Id inexistente, retorna exception")
    void buscarPorIdIncorreto() {
        UUID id = UUID.randomUUID();
        Mockito.when(eventoRepository.findById(id)).thenReturn(Optional.empty());
        NaoEncontradoException ex = assertThrows(NaoEncontradoException.class, () -> eventoService.buscarPorId(id));

        assertEquals("O objeto evento não foi encontrado(a)", ex.getMessage());
    }

}