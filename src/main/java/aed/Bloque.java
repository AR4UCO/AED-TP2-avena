package aed;

public class Bloque implements ListaPrioridad<Transaccion> {
    private Transaccion[] interno;
    private int tamaño;
    private int montoTotal;
    private int[] internoT; //N por orden de transaccion
    private boolean creacion;

    public Bloque(Transaccion[] transacciones) {
        interno = transacciones;
        tamaño = transacciones.length;
        montoTotal = 0;
        creacion = false;
        if (transacciones.length != 0) {            
            for (Transaccion T : transacciones) {
                if (T.id_comprador() != 0) {
                    montoTotal += T.monto();
                } else {
                    creacion = true;
                }
            }
            int n = tamaño -1;
            while (n >= 0) {
                heapifyDown(n);
                n -= 1;
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
        return tamaño;
    }

    public Transaccion mayorValor() {
        return interno[0];
    }


    public void eliminar() {
        
    }

    private void actualizar(int padre, int hijo) {
        Transaccion TransaccionBaja = interno[padre];
        Transaccion TransaccionSube = interno[hijo];

        interno[padre] = TransaccionSube;
        interno[hijo] = TransaccionBaja;
    }

    private void heapifyDown(int n) {
        int HijoIzq = (2*n)+1;
        int HijoDer = (2*n)+2;
        int mod = n;
        Transaccion TransaccionBaja = interno[n];

        while(HijoIzq < tamaño) {
            Transaccion HijoIT = interno[HijoIzq];
            if (HijoDer < tamaño) {
                Transaccion HijoDT = interno[HijoDer];
                int c = interno[HijoIzq].compareTo(interno[HijoDer]);
                if (c == 1) {
                    int s = TransaccionBaja.compareTo(HijoIT);
                    if (s == -1) {
                        actualizar(mod, HijoIzq);
                        mod = HijoIzq;
                    } else {}
                } else if (c == -1) {
                    int s = TransaccionBaja.compareTo(HijoDT);
                    if (s == -1) {
                        actualizar(mod, HijoDer);
                        mod = HijoDer;
                } else {}
                } 
            }else { 
                int s = TransaccionBaja.compareTo(HijoIT);
                if (s == -1) {
                    actualizar(mod, HijoIzq);
                    mod = HijoIzq;
                }
            }

            TransaccionBaja = interno[mod];
            HijoIzq = (2*mod)+1;
            HijoDer = (2*mod)+2;
            
        }
    } 
}
