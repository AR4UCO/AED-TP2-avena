package aed;

public class BloqueDeTransacciones {
    private ColaPrioridad<Transaccion> CPT;
    private boolean creacion;
    private int montoTotal;
    private int largoOriginal;

    public BloqueDeTransacciones(Transaccion[] transacciones) {
        Transaccion[] interno = transacciones.clone();
        CPT = new ColaPrioridad<Transaccion>(interno);

        montoTotal = 0;
        creacion = false;
        largoOriginal = transacciones.length;

        for (Transaccion T : transacciones) {
            if (T.id_comprador() != 0) {
                montoTotal += T.monto();
            } else {
                creacion = true;
            }
        }
    }

    public boolean Creacion() {
        return creacion;
    }

    public int montoTotal() {
        return montoTotal;
    }

    public void hackear() {
        Transaccion Raiz = CPT.maximo();

        if (Raiz.id_comprador() != 0) {
            montoTotal -= Raiz.monto();
        } else { //nada👍
            }
        CPT.desencolar();
    }

    public Transaccion mayorValor() {
        return CPT.maximo();
    }


    public int montoMedio() {
        if (creacion) {
            if (CPT.size() == 1) {
                return 0;
            } else {
                return montoTotal / CPT.size()-1;
            }
        } else {
            return montoTotal / CPT.size();
        }
    }

    public Transaccion[] BloquexId() {
        Transaccion[] res = new Transaccion[CPT.size()];
        int c = 0;
        if (CPT.size() == 0) {
            return res;
        }
        for (int i = 0; i < largoOriginal; i++) {
            if (CPT.obtenerHandle(i) != -1) {
                res[c] = CPT.obtener(CPT.obtenerHandle(i));
                c += 1;
            }
        }
        return res;
    }
}

