package com.bridj.example.bridjcodesolution.dao;

public interface Filterable<E> {
    public boolean pass(E o);
}
