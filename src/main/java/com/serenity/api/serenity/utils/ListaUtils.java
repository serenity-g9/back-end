package com.serenity.api.serenity.utils;

import java.util.stream.Stream;

public class ListaUtils {
    class Vetor<T> {
        private T objeto;

        public Vetor(T objeto) {
            this.objeto = objeto;
        }

        public T getObjeto() {
            return objeto;
        }

        public void setObjeto(T objeto) {
            this.objeto = objeto;
        }

        @Override
        public String toString() {
            return "Vetor contém: " + objeto.toString();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Vetor<?> vetor = (Vetor<?>) obj;
            return objeto.equals(vetor.objeto);
        }
    }

    public class VetorGenerico<T> {
        private Vetor<T>[] vetor;
        private int tamanho;

        public VetorGenerico(int capacidade) {
            vetor = new Vetor[capacidade];
            tamanho = 0;
        }

        public void adicionar(T objeto) {
            if (tamanho < vetor.length) {
                vetor[tamanho++] = new Vetor<>(objeto);
            } else {
                System.out.println("Vetor cheio. Não é possível adicionar mais itens.");
            }
        }

        public void removerPorIndice(int indice) {
            if (indice >= 0 && indice < tamanho) {
                System.arraycopy(vetor, indice + 1, vetor, indice, tamanho - indice - 1);
                vetor[--tamanho] = null;
            } else {
                System.out.println("Índice inválido.");
            }
        }

        public void removerPorObjeto(T objeto) {
            for (int i = 0; i < tamanho; i++) {
                if (vetor[i].getObjeto().equals(objeto)) {
                    removerPorIndice(i);
                    return;
                }
            }
            System.out.println("Objeto não encontrado.");
        }

        public void alterar(int indice, T novoObjeto) {
            if (indice >= 0 && indice < tamanho) {
                vetor[indice].setObjeto(novoObjeto);
            } else {
                System.out.println("Índice inválido.");
            }
        }

        public void exibir() {
            Stream.of(vetor)
                    .limit(tamanho)
                    .forEach(System.out::println);
        }
    }

}
