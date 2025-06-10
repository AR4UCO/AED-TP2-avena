package aed;

public class Bloque implements ListaPrioridad<Transaccion> {
    private Transaccion[] interno;
    private int tamaño;
    private int montoTotal;
    private int[] internoT; //N por orden de transaccion

    public Bloque(Transaccion[] transacciones) {
        interno = transacciones;
        tamaño = transacciones.length;
        if (transacciones.length != 0) {
            int n = tamaño -1;
            while (n >= 0) {
                heapifyDown(n);
                n -= 1;
            }
        }
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

    private void actualizar() {
    }

    private void heapifyDown(int n) {
        int HijoIzq = (2*n)+1;
        int HijoDer = (2*n)+2;
        Transaccion TransaccionBaja = interno[n];

        if(HijoIzq < tamaño) {
            Transaccion HijoIT = interno[HijoIzq];
            if (HijoDer < tamaño) {
                Transaccion HijoDT = interno[HijoDer];
                int c = interno[HijoIzq].compareTo(interno[HijoDer]);
                if (c == 1) {
                    int s = TransaccionBaja.compareTo(HijoIT);
                    if (s == -1) {
                        interno[n] = HijoIT;
                        interno[HijoIzq] = TransaccionBaja;
                    } else {}
                } else if (c == -1) {
                    int s = TransaccionBaja.compareTo(HijoDT);
                    if (s == -1) {
                        interno[n] = HijoDT;
                        interno[HijoDer] = TransaccionBaja;
                } else {}
                } 
            }else { 
                int s = TransaccionBaja.compareTo(HijoIT);
                if (s == -1) {
                    interno[n] = HijoIT;
                    interno[HijoIzq] = TransaccionBaja;
                }
            }
        }
    }   //N esto hay que implementar para que siga bajando todo lo que sea necesario (y seguramente se puede emprolijar significativamente)
}
