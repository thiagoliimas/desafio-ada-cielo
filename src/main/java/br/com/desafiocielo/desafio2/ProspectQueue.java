package br.com.desafiocielo.desafio2;

public class ProspectQueue<T> implements Queue<T>{

    private Object[] e;

    private final int init;

    private int count;

    public ProspectQueue() {
        this.init = 1;
        this.e = new Object[100];
    }

    @Override
    public T addQ(T e) {
        if(this.e.length >= this.count){
            this.increase();
        }
        this.e[count++] = e;
        return e;
    }

    @Override
    public T removeQ() throws NoSuchFieldException {
        if (this.count == 0) {
            throw new NoSuchFieldException("Fila vazia");
        }

        T element = this.get(0);
        System.arraycopy(this.e, 1, this.e, 0, this.e.length - 1);
        this.count--;

        return element;
    }

    @Override
    public int lengthQ() {
        return this.count;
    }

    @Override
    public T first() {
        return this.get(0);
    }

    @Override
    public T last() {
        return this.get(this.count-1);
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    private void increase(){
        Object[] array = new Object[this.e.length*2];
        System.arraycopy(this.e, 0, array, 0, this.e.length);
        this.e = array;
    }

    @Override
    public T get(int i){
        return (T) this.e[i];
    }

    @Override
    public boolean exist(T f){
        boolean exist = false;
        for (int i = 0; i < count ; i++) {
            if (f.equals(e[i])) {
                exist = true;
                break;
            }
        }
        return exist;
    }
}
