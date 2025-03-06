package com.serenity.api.serenity.services;

import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.Anexo;
import com.serenity.api.serenity.repositories.AnexoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AnexoServiceTest {
    @Mock
    private AnexoRepository anexoRepository;

    @InjectMocks
    private AnexoService anexoService;


//    @Test
//    @DisplayName("Nada no banco, lista vazia")
//    void buscarTodos() {
//        List<Anexo> lista = Collections.emptyList();
//        Mockito.when(anexoRepository.findAll()).thenReturn(lista);
//        List<Anexo> resultado = anexoService.listar();
//        assertNotNull(resultado);
//        assertTrue(resultado.isEmpty());
//    }

//    @Test
//    @DisplayName("banco com informação, retorna lista com resultados")
//    void buscarTodosListaCheia() {
//        List<Anexo> Anexos = List.of(
//                new Anexo(
//                ),
//                new Anexo(
//                )
//        );

//        Mockito.when(anexoRepository.findAll()).thenReturn(Anexos);
//
//        List<Anexo> resultado = anexoService.listar();
//
//        assertNotNull(resultado);
//        assertFalse(resultado.isEmpty());
//        assertEquals(Anexos.size(), resultado.size());
//        assertArrayEquals(Anexos.toArray(), resultado.toArray());
//    }


    @Test
    @DisplayName("Anexo pelo id existente, retorne corretamente")
    void buscarPorIdCorretamente() {
        UUID id = UUID.randomUUID();
        Anexo AnexoEsperado = new Anexo(
                id,
                "Teste1",
                "https://www.google.com/imgres?q=imagens&imgurl=https%3A%2F%2Fmedia.istockphoto.com%2Fid%2F517188688%2Fpt%2Ffoto%2Fpaisagem-de-montanha.jpg%3Fs%3D612x612%26w%3D0%26k%3D20%26c%3DuFGUrUT6gA8FrTWhE10YYzngWPlDLssKxJiDs1Qw2Qs%3D&imgrefurl=https%3A%2F%2Fwww.istockphoto.com%2Fbr%2Fbanco-de-imagens%2Fnatureza-e-paisagens&docid=ttmosThLtFCTcM&tbnid=oa7_1poSvrt70M&vet=12ahUKEwi1sIvB9fOLAxWXHrkGHVncFlwQM3oECGYQAA..i&w=612&h=384&hcb=2&ved=2ahUKEwi1sIvB9fOLAxWXHrkGHVncFlwQM3oECGYQAA",
                1
        );

        Mockito.when(anexoRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(AnexoEsperado));
        Anexo resultado = anexoService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(AnexoEsperado.getId(), resultado.getId());
    }

    @Test
    @DisplayName("Id inexistente, retorna exception")
    void buscarPorIdIncorreto() {
        UUID id = UUID.randomUUID();
        Mockito.when(anexoRepository.findById(id)).thenReturn(Optional.empty());
        NaoEncontradoException ex = assertThrows(NaoEncontradoException.class, () -> anexoService.buscarPorId(id));

        assertEquals("O objeto Anexo não foi encontrado(a)", ex.getMessage());
    }

}