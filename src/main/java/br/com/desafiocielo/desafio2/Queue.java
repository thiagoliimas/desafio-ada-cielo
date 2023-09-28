package br.com.desafiocielo.desafio2;

public interface Queue<T> {

    T addQ(T e);

    T removeQ() throws NoSuchFieldException;

    T first();

    T last();

    int lengthQ();

    boolean isEmpty();

    public T get(int i);
}
