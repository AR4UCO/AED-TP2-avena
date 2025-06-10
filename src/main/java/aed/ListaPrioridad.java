package aed;

public interface ListaPrioridad<T> {

    public T mayorValor();

    public void agregarAtras();

    public void agregarAdelante();

    public int montoTotal();

    public int longitud();

    public void eliminar();
}
