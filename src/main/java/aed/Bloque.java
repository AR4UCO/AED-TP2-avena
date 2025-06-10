package aed;

public class Bloque implements ListaPrioridad<Transaccion> {
    private Transaccion[] interno;
    private int tamaño;
    private int montoTotal;
    private int[] handles; //N por orden de transaccion

    public Bloque(Transaccion[] transacciones) {
        interno = new Transaccion[transacciones.length];
    }

    public int montoTotal() {
        return montoTotal;
    }

    public int longitud() {
        return tamaño;
    }

    public Transaccion mayorValor() {
        return interno[0];
    }

    public void agregarAdelante() {
    }

    public void agregarAtras() {
    }

    public void eliminar() {
    }

    private void actualizar() {
    }

    private void heapify() {
    }
}
