package com.serenity.api.serenity.utils;

import com.serenity.api.serenity.interfaces.Ordenavel;

import java.util.List;

public class SortUtil {

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
