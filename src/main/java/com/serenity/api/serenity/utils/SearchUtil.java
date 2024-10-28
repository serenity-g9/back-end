package com.serenity.api.serenity.utils;

import com.serenity.api.serenity.models.Evento;

import java.util.Objects;

public class SearchUtil {
    public static int binarySearch(Evento[] vetor, String indiceBuscado) {
        int inferior = 0;
        int superior = vetor.length - 1;
        int meio;

        while (inferior <= superior) {  // Use while instead of for loop
            meio = (inferior + superior) / 2;

            if (Objects.equals(indiceBuscado, vetor[meio].getNome())) {
                return meio;  // Found the target, return index
            } else if (indiceBuscado.compareTo(vetor[meio].getNome()) > 0) {
                inferior = meio + 1;  // Search in the right half
            } else {
                superior = meio - 1;  // Search in the left half
            }
        }
        return -1;  // Target not found
    }
}
