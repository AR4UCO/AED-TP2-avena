package aed;

public class Saldos implements DiccionarioOrdenado<Saldos.Usuario> {
    private Usuario[] saldos;
    private int[] handles;


    public class Usuario implements Comparable<Usuario> {   
        private int id;
        private int balance;
    

        Usuario(int u) {id = u; balance = 0;}

        @Override
            public int compareTo(Usuario n) {
            int v = 0;
            if (this.balance > n.balance || (this.balance == n.balance && this.id < n.id)) {
                v = 1; // el original va arriba
            } else {
                v = -1; // el segundo va arriba
            }
            return v;
        }
    }

    public Saldos(int n) {
        int c = 1;
        saldos = new Usuario[n];
        handles = new int[n+1];
        handles[0] = -1;

        while (c != n+1) {
            saldos[c-1] = new Usuario(c);
            handles[c] = c-1;
            c += 1;
        }
    }
    
    public int maximoTenedor() {
        return saldos[0].id;
    }

    public void actualizar(int p, int v) {
        int i = handles[p];
        saldos[i].balance = saldos[i].balance + v;

        if (v > 0 && i != 0) {
            while (saldos[i].compareTo(saldos[(i-1)/2]) == -1) {
                   Usuario x = saldos[(i-1)/2];
                   saldos[(i-1)/2] = saldos[i];
                   saldos[i] = x;


                   handles[p] = (i-1)/2;
                   handles[saldos[i].id] = i;

                   i = handles[p];
            // habria que re-implementarlo recursivamente para seguir chequeando hacia arriba lo que haga falta (esto es solo una base)
            }
        } else if (v < 0) {
            int hijoMayor = saldos[(2*i)+1].compareTo(saldos[(2*i)+2]);
            if (hijoMayor == -1) {
                Usuario x = saldos[i];
                saldos[i] = saldos[(2*i)+1];
                saldos[(2*i)+1] = x;
            } else {
                Usuario x = saldos[i];
                saldos[i] = saldos[(2*i)+2];
                saldos[(2*i)+2] = x;
            } //estos dos tambien hay que hacerlos una llamada recursiva para seguir chequeando todo lo que haga falta hacia abajo

        }
    }

    private void heapifyUp() {

    }
    
    private void heapifyDown() {

    }

    

}