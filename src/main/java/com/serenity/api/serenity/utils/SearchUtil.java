package com.serenity.api.serenity.utils;

import com.serenity.api.serenity.models.Evento;

import java.util.Objects;

public class SearchUtil {
    public static int binarySearch(Evento[] vetor, String indiceBuscado) {
        int inferior = 0;
        int superior = vetor.length - 1;
        int meio;

        while (inferior <= superior) {
            meio = (inferior + superior) / 2;

            if (Objects.equals(indiceBuscado, vetor[meio].getNome())) {
                return meio;
            } else if (indiceBuscado.compareTo(vetor[meio].getNome()) > 0) {
                inferior = meio + 1;
            } else {
                superior = meio - 1;
            }
        }
        return -1;
    }
}
