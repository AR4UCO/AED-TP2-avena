package aed;

interface Secuencia<T> {

    public int longitud();

    public void agregarAtras(T elem);

    public T obtener(int i);
}
