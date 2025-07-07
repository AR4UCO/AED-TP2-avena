package aed;

public class Saldos {

    private ColaPrioridad<Usuario> colaDeUsuarios;
    private Usuario[] sal;

    public class Usuario implements Comparable<Usuario> {

        private int id;
        private int balance;

        Usuario(int u) {
            id = u;
            balance = 0;
        }

        public int obtid() {
            return id;
        }

        @Override
        public int compareTo(Usuario n) {
            int v = 0;
            if (this.balance > n.balance) {
                return 1;
            } else if (this.balance < n.balance) {
                return -1;
            } else {
                if (this.id < n.id) {
                    return 1;
                } else if (this.id > n.id) {
                    return -1;
                } else {
                    return 0;
                }

            }
        }
    }

    public Saldos(int n) { // ver
        sal = new Usuario[n + 1];
        int c = 1;
        while (c <= n) { // O(n)
            sal[c] = new Usuario(c);
            c += 1;
        }
        Usuario[] data = new Usuario[n];
        int i = 0;
        while (i < n) {
            data[i] = sal[i + 1];
            i++;
        }
        colaDeUsuarios = new ColaPrioridad<Usuario>(data);
    }

    public int maximoTenedor() {
        return colaDeUsuarios.maximo().obtid();
    }

    public void actualizar(int handle, int subeObaja) {
        sal[handle].balance += subeObaja;
        colaDeUsuarios.actualizar(handle - 1, subeObaja);
    }

}
