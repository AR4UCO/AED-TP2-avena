package aed;

public class Saldos implements BDDUsuarios<Saldos.Usuario> {
    private Usuario[] saldos;
    private int tamaño;
    private int[] handles;

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
        saldos = new Usuario[n];
        handles = new int[n + 1];
        handles[0] = -1;
        tamaño = n;

        while (c != n + 1) { // O(n)
            saldos[c - 1] = new Usuario(c);
            handles[c] = c - 1;
            c += 1;
        }
    }

    // La complejidad es O(n) ya que el constructor recorre una vez el rango de 1 a n para iniciar,
    // no realiza ninguna tarea que aumente esta complejidad

    public int maximoTenedor() {
        return saldos[0].id;
    }

    public void actualizar(int p, int v) {
        int i = handles[p];
        saldos[i].balance = saldos[i].balance + v;
        if (v > 0) { // O(Log n)
            int iPadre = (i - 1) / 2;
            while (saldos[i].compareTo(saldos[iPadre]) == 1 && i != 0) { // O(Log n)
                heapify(i, iPadre);
                i = handles[p];
                iPadre = (i - 1) / 2;
            }
        } else if (v < 0) {
            boolean sigoBajando = true;
            int ihijoIzq = (2 * i) + 1;
            int ihijoDer = (2 * i) + 2;
            while (tieneHijos(i) != 0 && sigoBajando) { // O(Log n)
                if (tieneHijos(i) == 1) {
                    if (saldos[i].compareTo(saldos[ihijoIzq]) == -1) {
                        heapify(ihijoIzq, i);
                    } else {
                        sigoBajando = false;
                    }
                } else if (tieneHijos(i) == 2 && (saldos[i].compareTo(saldos[ihijoIzq]) == -1
                        || saldos[i].compareTo(saldos[ihijoDer]) == -1)) {
                    int hijoMayor = saldos[ihijoIzq].compareTo(saldos[ihijoDer]);
                    if (hijoMayor == 1) { // N hijo izquiero es el mayor
                        heapify(ihijoIzq, i);
                        i = handles[p];
                        ihijoIzq = (2 * i) + 1;
                        ihijoDer = (2 * i) + 2;
                    } else { // N hijo derecho es el mayor
                        heapify(ihijoDer, i);
                        i = handles[p];
                        ihijoIzq = (2 * i) + 1;
                        ihijoDer = (2 * i) + 2;
                    }
                } else {
                    sigoBajando = false;
                }
            }
        } else {
        }
    }

    private void heapify(int UsuarioSube, int UsuarioBaja) {
        Usuario padreAnterior = saldos[UsuarioBaja];
        saldos[UsuarioBaja] = saldos[UsuarioSube];
        saldos[UsuarioSube] = padreAnterior;

        handles[saldos[UsuarioBaja].id] = UsuarioBaja;
        handles[saldos[UsuarioSube].id] = UsuarioSube;
    }

    private int tieneHijos(int i) {
        int res = 0;
        if (tamaño > (2 * i) + 1) {
            res += 1;
        }
        if (tamaño > (2 * i) + 2) {
            res += 1;
        }
        return res;
    }
}
