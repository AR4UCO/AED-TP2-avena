package aed;

public class Saldos {
    private ColaPrioridad<Transaccion> colaDeUsuarios;
    private int tamaño;
    private int[] handles;
    private Usuario[] sal;
    

    public class Usuario implements Comparable<Usuario> {
        private int id;
        private int balance;

        Usuario(int u) {
            id = u;
            balance = 0;
        }

        @Override
        public int compareTo(Usuario n) {
            int v = 0;
            if (this.balance > n.balance || (this.balance == n.balance && this.id < n.id)) {
                v = 1; // N el original va arriba
            } else {
                v = -1; // N el segundo va arriba
            }
            return v;
        }
    }

    public Saldos(int n) {
        int c = 1;
        sal = new Usuario[n];
        tamaño = n;

        while (c != n + 1) { // O(n)
            sal[c - 1] = new Usuario(c);
            c += 1;
        }
        colaDeUsuarios = new ColaPrioridad<Usuario>(sal);
    }


    public int maximoTenedor() {
        return colaDeUsuarios.maximo().id();
    }

   
}