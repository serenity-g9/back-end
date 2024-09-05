package com.serenity.api.serenity.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface Ordenavel<T extends Comparable> {
    @JsonIgnore
    T getValorOrdenacao();
}
