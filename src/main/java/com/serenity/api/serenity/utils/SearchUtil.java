package com.serenity.api.serenity.utils;

public class SearchUtil {
        public static int binarySearch(int[] vetor,int indiceBuscado){
            int contador = 0;
            int inferior = 0,
                    superior = vetor.length-1,
                    meio;

            for (int i = 0; i < vetor.length; i++) {
                contador++;
                meio = (inferior+superior)/2;
                if (indiceBuscado == vetor[meio]){
                    System.out.println(contador);
                    return meio;
                }else if(indiceBuscado > vetor[meio]){
                    inferior = meio+1;
                }else{
                    superior = meio-1;
                }
            }
            System.out.println(contador);
            return -1;
        }
}
