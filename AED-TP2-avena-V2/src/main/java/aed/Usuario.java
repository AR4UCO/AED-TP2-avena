package aed;

public class Usuario implements Identificable<Usuario> {
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

    @Override
    public int getId() {
        return this.id;
    }
}