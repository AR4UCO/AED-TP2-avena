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

        if (v > 0 && i != 0) {
            while (saldos[i].compareTo(saldos[(i-1)/2]) == 1) {
                   Usuario padreAnt = saldos[(i-1)/2];
                   saldos[(i-1)/2] = saldos[i];
                   saldos[i] = padreAnt;


                   handles[p] = (i-1)/2;
                   handles[saldos[i].id] = i;

                   i = handles[p];

            }
        } else if (v < 0) {
            if (tieneHijos(p) == 2) {
                while (saldos[i].compareTo(saldos[(2*i)+1]) == -1 || saldos[i].compareTo(saldos[(2*i)+2]) == -1) {
                
                    int hijoMayor = saldos[(2*i)+1].compareTo(saldos[(2*i)+2]);
                
                
                    if (hijoMayor == 1) { // hijo izquiero es el mayor
                        Usuario padreAnt = saldos[i];
                        saldos[i] = saldos[(2*i)+1];
                        saldos[(2*i)+1] = padreAnt;

                        handles[p] = (2*i)+1;
                        handles[saldos[i].id] = i;

                        i = handles[p];

                    } else { // hijo derecho es el mayor
                        Usuario padreAnt = saldos[i];
                        saldos[i] = saldos[(2*i)+2];
                        saldos[(2*i)+2] = padreAnt;

                        handles[p] = (2*i)+2;
                        handles[saldos[i].id] = i;

                        i = handles[p];
                    }
                }
            } else if (tieneHijos(p) == 1) {
                while (saldos[i].compareTo(saldos[(2*i)+1]) == -1) {
                    Usuario padreAnt = saldos[i];
                    saldos[i] = saldos[(2*i)+1];
                    saldos[(2*i)+1] = padreAnt;

                    handles[p] = (2*i) +1;
                    handles[saldos[i].id] = i;

                    i = handles[p];
                }
            } else {}
        }
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

    

}
