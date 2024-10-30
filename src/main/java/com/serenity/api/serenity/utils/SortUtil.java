package com.serenity.api.serenity.utils;

import com.serenity.api.serenity.dtos.formulario.RespostaUsuario;
import com.serenity.api.serenity.interfaces.Ordenavel;

import java.util.List;

public class SortUtil {

    public static <T extends Ordenavel> void quickSort(T[] v, int indInicio, int indFim) {
        int i = indInicio;
        int j = indFim;
        Comparable pivo = v[(indInicio + indFim) / 2].getValorOrdenacao();

        while (i <= j) {
            while (i < indFim && v[i].getValorOrdenacao().compareTo(pivo) < 0) {
                i = i + 1;
            }

            while (j > indInicio && v[i].getValorOrdenacao().compareTo(pivo) > 0) {
                j = j - 1;
            }
            if (i <= j) {
                T aux = v[i];
                v[i] = v[j];
                v[j] = aux;
                i = i + 1;
                j = j - 1;
            }
        }
        if (indInicio < j) {
            quickSort(v,indInicio, j);
        }
        if (i < indFim) {
            quickSort(v, i, indFim);
        }
    }

    public static <T extends Ordenavel> List<T> selectionSort(T[] v) {

        for (int i = 0; i < v.length; i++) {
            int k = i;

            for (int j = i+1; j < v.length; j++) {
                if (v[j].getValorOrdenacao().compareTo(v[k].getValorOrdenacao()) > 0 ) k = j;
            }

            T aux = v[i];
            v[i] = v[k];
            v[k] = aux;
        }

        return List.of(v);
    }
}
