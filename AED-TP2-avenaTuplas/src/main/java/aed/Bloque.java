package aed;

import javax.xml.namespace.QName;

import sun.util.resources.cldr.ext.TimeZoneNames_zh_Hans_SG;

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
        if (this.longitud() == 0) {
            return res;
        }
        int c = 0;
        int i = 0;
        while (i < interno.length) {
            if (colaDetransacciones.obtenerPosicion(i) != -1) {
                res[c] = interno[i]; //Veerifico que la posicion en i no sea -1 y accedo a ella.
                c += 1;
            }
            i += 1;

        }
        return res;
    }

    public void eliminarMaximo() {
        if (colaDetransacciones.maximo().id_comprador() != 0) {
            montoTotal -= colaDetransacciones.maximo().monto();
        }
        colaDetransacciones.desencolar();
    }
}
