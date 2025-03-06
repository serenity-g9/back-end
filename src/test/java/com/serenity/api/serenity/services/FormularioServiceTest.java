package com.serenity.api.serenity.services;

import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.Evento;
import com.serenity.api.serenity.models.Formulario;
import com.serenity.api.serenity.repositories.FormularioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class FormularioServiceTest {
    @Mock
    private FormularioRepository formularioRepository;

    @InjectMocks
    private FormularioService formularioService;


    @Test
    @DisplayName("Nada no banco, lista vazia")
    void buscarTodos() {
        List<Formulario> lista = Collections.emptyList();
        Mockito.when(formularioRepository.findAll()).thenReturn(lista);
        List<Formulario> resultado = formularioService.listar();
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("banco com informação, retorna lista com resultados")
    void buscarTodosListaCheia() {
        List<Formulario> Formularios = List.of(
                new Formulario(
                        "1",
                        "formulario1",
                        List.of(new Evento(),new Evento())
                ),
                new Formulario(
                        "1",
                        "formulario1",
                        List.of(new Evento(),new Evento())
                )
        );

        Mockito.when(formularioRepository.findAll()).thenReturn(Formularios);

        List<Formulario> resultado = formularioService.listar();

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(Formularios.size(), resultado.size());
        assertArrayEquals(Formularios.toArray(), resultado.toArray());
    }


    @Test
    @DisplayName("Formulario pelo id existente, retorne corretamente")
    void buscarPorIdCorretamente() {
        Formulario FormularioEsperado = new Formulario(
                "1",
                "formulario1",
                List.of(new Evento(),new Evento())
        );

        Mockito.when(formularioRepository.findById("1")).thenReturn(Optional.of(FormularioEsperado));
        Formulario resultado = formularioService.buscarPorId("1");

        assertNotNull(resultado);
        assertEquals(FormularioEsperado.getId(), resultado.getId());
    }

    @Test
    @DisplayName("Id inexistente, retorna exception")
    void buscarPorIdIncorreto() {
        UUID id = UUID.randomUUID();
        Mockito.when(formularioRepository.findById("1")).thenReturn(Optional.empty());
        NaoEncontradoException ex = assertThrows(NaoEncontradoException.class, () -> formularioService.buscarPorId("1"));

        assertEquals("O objeto formulário não foi encontrado(a)", ex.getMessage());
    }
}