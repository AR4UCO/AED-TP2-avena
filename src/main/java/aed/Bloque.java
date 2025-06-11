package aed;

public class Bloque implements ListaPrioridad<Transaccion> {
    private Transaccion[] interno;
    private int tamaño;
    private int montoTotal;
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


    public void eliminarMaximo() {
        Transaccion Raiz = interno[0];
        Transaccion Ultimo = interno[tamaño-1];

        interno[0] = Ultimo;
        interno[tamaño-1] = Raiz;

        tamaño -= 1;
        if (Raiz.id_comprador() != 0) {
            montoTotal -= Raiz.monto();
        } else {}

        heapifyDown(0);
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
        boolean sigoBajando = true;
        
        while(HijoIzq < tamaño && sigoBajando) {
            Transaccion HijoIT = interno[HijoIzq];
            if (HijoDer < tamaño) {
                Transaccion HijoDT = interno[HijoDer];
                int c = interno[HijoIzq].compareTo(interno[HijoDer]);
                if (c == 1) {
                    int s = TransaccionBaja.compareTo(HijoIT);
                    if (s == -1) {
                        actualizar(mod, HijoIzq);
                        mod = HijoIzq;
                    } else {
                        sigoBajando = false;
                    }
                } else if (c == -1) {
                    int s = TransaccionBaja.compareTo(HijoDT);
                    if (s == -1) {
                        actualizar(mod, HijoDer);
                        mod = HijoDer;
                    } else {
                        sigoBajando = false;
                    }
                } 
            }else { 
                int s = TransaccionBaja.compareTo(HijoIT);
                if (s == -1) {
                    actualizar(mod, HijoIzq);
                    mod = HijoIzq;
                } else {
                    sigoBajando = false;
                }
            }

            TransaccionBaja = interno[mod];
            HijoIzq = (2*mod)+1;
            HijoDer = (2*mod)+2;
            
        }
    } 
}