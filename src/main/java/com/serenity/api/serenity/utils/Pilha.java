package com.serenity.api.serenity.utils;

import lombok.Getter;

import java.util.Arrays;

public class Pilha<T> {

    private final T[] pilha;

    @Getter
    private int topo;

    public Pilha(int capacidade) {
        this.pilha = (T[]) new Object[capacidade];
        this.topo = -1;
    }

    public Boolean isEmpty() {
        return this.topo == -1;
    }

    public Boolean isFull() {
        return this.topo == this.pilha.length - 1;
    }

    public void push(T info) {

        if (this.isFull()) throw new IllegalStateException("Pilha cheia");

        this.pilha[++topo] = info;
    }

    public T pop() {
        if (this.isEmpty()) return null;

        return pilha[topo--];
    }

    public T peek() {
        if (this.isEmpty()) return null;

        return pilha[topo];
    }

    public void exibe() {
        if (this.isEmpty()) System.out.println("Pilha vazia");
        else System.out.println(Arrays.toString(this.pilha));
    }

}
