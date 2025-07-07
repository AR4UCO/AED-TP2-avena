package aed;

public class Bloque {
    private ColaPrioridad<Transaccion> colaDetransacciones;
    private boolean creacion;
    private Transaccion[] interno;
    private int montoTotal;

    public Bloque(Transaccion[] transacciones) {
        interno = transacciones.clone();
        colaDetransacciones = new ColaPrioridad<Transaccion>(interno);

        montoTotal = 0;
        creacion = false;
        for (Transaccion T : transacciones) {
            if (T.id_comprador() != 0) {
                montoTotal += T.monto();
            } else {
                creacion = true;
            }

        }
    }

    public boolean tieneCreacion() {
        return creacion;
    }

    public int montoTotal() {
        return montoTotal;
    }

    public int longitud() {
        return colaDetransacciones.size();
    }

    public Transaccion mayorValor() {
        return colaDetransacciones.maximo();
    }

    public Transaccion[] bloquexId() { //tengo que ver como acceder al segundo elemento de cada tupla
        Transaccion[] res = new Transaccion[this.longitud()];
        int c = 0;
        if (this.longitud() == 0) {
            return res;
        }
        int i = 0;
        while (i<interno.length){
            if (colaDetransacciones.obtenerPosicion(i) != -1) {
                res[c] = interno[colaDetransacciones.obtenerPosicion(i)];
                c += 1;
            }
            i +=1;

        }
        return res;
    }

    public void eliminarMaximo() {
        //if (colaDetransacciones[0].primero.id_comprador() != 0) {
         //   montoTotal -= colaDetransacciones[0].primero.monto();
        //}
        colaDetransacciones.desencolar();
    }
}
