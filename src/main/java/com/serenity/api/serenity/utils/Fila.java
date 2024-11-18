package com.serenity.api.serenity.utils;

import lombok.Getter;

import java.util.Arrays;

public class Fila<T> {
    @Getter
    private int tamanho;
    private final T[] fila;

    public Fila(int capacidade) {
        this.tamanho = 0;
        this.fila = (T[]) new Object[capacidade];
    }

    public boolean isEmpty() {
        return this.tamanho == 0;
    }

    public boolean isFull() {
        return this.tamanho == this.fila.length;
    }

    public void insert(T info) {
        if (isFull()) throw new IllegalStateException();

        this.fila[tamanho++] = info;
    }

    public T peek() {
        return this.fila[0];
    }

    public T poll() {

        if (isEmpty()) return null;

        T first = this.peek();

        for (int i = 0; i < getTamanho()-1; i++) {
            this.fila[i] = this.fila[i+1];
        }

        tamanho--;

        return first;
    }

    public void exibe() {
        System.out.println(Arrays.toString(this.fila));
    }
}

