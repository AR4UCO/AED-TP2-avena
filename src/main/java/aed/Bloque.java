package aed;

public class Bloque implements ListaPrioridad<Transaccion> {
    private Transaccion[] interno;
    private int[] handleB;
    private int corrimiento;
    private int tamaño;
    private int montoTotal;
    private boolean creacion;

    public Bloque(Transaccion[] transacciones) {
        interno = transacciones.clone();
        handleB = new int[tamaño];
        tamaño = transacciones.length;
        montoTotal = 0;
        creacion = false;
        if (transacciones.length != 0) {
            corrimiento = transacciones[0].id();
            handleB = new int[tamaño];
            int i = tamaño - 1;
            for (Transaccion T : transacciones) {
                handleB[i] = i;
                if (T.id_comprador() != 0) {
                    montoTotal += T.monto();
                } else {
                    creacion = true;
                }
                i--;
            }
            int n = tamaño - 1;
            while (n >= 0) {
                heapifyDown(n);
                n -= 1;
            }
        }
    }
    /*
    para agregar bloque se inicializan las distintas variables y constantes que vamos a usar.
    para cada transaccion se actualiza el monto y se agrega al handle del heap - O(n)
    luego se hace heapify a la seq de transacciones - O(n) ya que usamos la técnica de construcción del heap bottom up
    entonces O(n)+O(n)=O(max{n,n})=O(n)
    */

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

    public Transaccion[] bloquexId() {
        Transaccion[] res = new Transaccion[this.longitud()];
        int c = 0;
        if (this.longitud() == 0) {
            return res;
        }
        for (int i = 0; i < interno.length; i++) {
            if (handleB[i] != -1) {
                res[c] = interno[handleB[i]];
                c += 1;
            }
        }
        return res;
    }
    /*
    para dar el ultimo bloque se recorre todo el array de handleB y se imprimen sus valores - O(n)
    n siendo la cantidad de transacciones
    */

    public void eliminarMaximo() {
        Transaccion Raiz = interno[0];
        Transaccion Ultimo = interno[tamaño - 1];

        handleB[Raiz.id() - corrimiento] = -1;
        handleB[Ultimo.id() - corrimiento] = 0;

        interno[0] = Ultimo;
        interno[tamaño - 1] = null;

        tamaño -= 1;
        if (Raiz.id_comprador() != 0) {
            montoTotal -= Raiz.monto();
        } else {
        }

        heapifyDown(0);
    }
    /*
    se rota al primer y ultimo elemento
    se baja al ultimo elemento para ordenar el heap (se desencola el heap) - O(log n)
    */

    private void actualizar(int padre, int hijo) {
        Transaccion TransaccionBaja = interno[padre];
        Transaccion TransaccionSube = interno[hijo];
        int indBaja = interno[padre].id() - corrimiento;
        int indSube = interno[hijo].id() - corrimiento;
        int hBaja = handleB[interno[padre].id() - corrimiento];
        int hSube = handleB[interno[hijo].id() - corrimiento];

        handleB[indBaja] = hSube;
        handleB[indSube] = hBaja;

        interno[padre] = TransaccionSube;
        interno[hijo] = TransaccionBaja;
    }

    private void heapifyDown(int n) {
        int HijoIzq = (2 * n) + 1;
        int HijoDer = (2 * n) + 2;
        int mod = n;
        Transaccion TransaccionBaja = interno[n];
        boolean sigoBajando = true;

        while (HijoIzq < tamaño && sigoBajando) {
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
            } else {
                int s = TransaccionBaja.compareTo(HijoIT);
                if (s == -1) {
                    actualizar(mod, HijoIzq);
                    mod = HijoIzq;
                } else {
                    sigoBajando = false;
                }
            }

            TransaccionBaja = interno[mod];
            HijoIzq = (2 * mod) + 1;
            HijoDer = (2 * mod) + 2;

        }
    }
    /*
    la funcion que actualiza el heap
    sift down - O(log(n)) 
    */


}
