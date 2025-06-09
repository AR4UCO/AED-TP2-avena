package aed;

public class Saldos implements BDDUsuarios<Saldos.Usuario> {
    private Usuario[] saldos;
    private int tamaño;
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
        tamaño = n;

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
        if (v > 0) {
            int iPadre = (i-1)/2;
            while (saldos[i].compareTo(saldos[iPadre]) == 1) {
                   heapify(i, iPadre);
                   i = handles[p];
                   iPadre = (i-1)/2;
            }
        } else if (v < 0) {
            int ihijoIzq = (2*i)+1; 
            if (tieneHijos(p) == 2) {

                int ihijoDer = (2*i)+2;
                while (saldos[i].compareTo(saldos[ihijoIzq]) == -1 || saldos[i].compareTo(saldos[ihijoDer]) == -1) {                   
                    int hijoMayor = saldos[ihijoIzq].compareTo(saldos[ihijoDer]);
                    if (hijoMayor == 1) { // hijo izquiero es el mayor
                        heapify(ihijoIzq, i);
                        i = handles[p];
                        ihijoIzq = (2*i)+1;
                        ihijoDer = (2*i)+2;
                    } else { // hijo derecho es el mayor
                        heapify(ihijoDer, i);
                        i = handles[p];
                        ihijoIzq = (2*i)+1;
                        ihijoDer = (2*i)+2;
                    }
                }
            } else if (tieneHijos(p) == 1 && (saldos[i].compareTo(saldos[ihijoIzq]) == -1)) { 
                //no necesito el while porque si tiene un solo hijo, el hijo esta en el ultimo nivel y entonces mas que una vez no se puede bajar
                    heapify(ihijoIzq, i);
                }
            } else {}
        }

    private void heapify(int UsuarioSube, int UsuarioBaja) {
        Usuario padreAnterior = saldos[UsuarioBaja];
        saldos[UsuarioBaja] = saldos[UsuarioSube];
        saldos[UsuarioSube] = padreAnterior;
        
        handles[UsuarioBaja] = saldos[UsuarioBaja].id;
        handles[UsuarioSube] = saldos[UsuarioSube].id;
    }


    private int tieneHijos(int i) {
        int res = 0;
        if (tamaño < (2*i)+1) {
            res += 1;
        }
        if (tamaño < (2*i)+2) {
            res += 1;
        }
        return res;
    }
}
