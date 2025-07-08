package aed;

public class ColaPrioridad<T extends Comparable<T>> {

    private Tupla<T>[] heapArray;
    private int tamaño;
    private int[] handleArray;

    public class Tupla<T> {

        public T primero;
        public int segundo;

        public Tupla(T primero, int segundo) {
            this.primero = primero;
            this.segundo = segundo;
        }
    }

    public ColaPrioridad(T[] base) { // Vamos a tener en "nuestroCaso" [(elemento, id)] donde posicion inicia en 1
        heapArray = new Tupla[base.length]; // creo un array de tuplas de determinada longitud
        tamaño = base.length;
        if (tamaño != 0) { // O(1)
            handleArray = new int[tamaño];
            int i = 0;
            while (i < tamaño) {
                heapArray[i] = new Tupla<>(base[i], i);
                handleArray[i] = i;
                i++;
            }
            int n = tamaño - 1;
            while (n >= 0) {
                heapifyDown(n);
                n -= 1;
            }
        }
    }

    public int size() {
        return tamaño;
    }

    public void actualizar(int handle, int subeObaja) { // 1. Handle 2.suma/resta Saldo. Complejidad: O(log(n))
        int i = handleArray[handle];

        //if (subeObaja == 1) {
        heapifyUp(i); //O(log(n))
        //} else if (subeObaja == -1) {
        heapifyDown(i); //O(log(n))
        //}
    }

    private void heapifyDown(int n) {
        boolean sigoBajando = true;
        int ihijoIzq = (2 * n) + 1;
        int ihijoDer = (2 * n) + 2;
        int mod = n;
        while (tieneHijos(mod) != 0 && sigoBajando) { // O(Log n)
            if (tieneHijos(mod) == 1) {
                if (heapArray[mod].primero.compareTo(heapArray[ihijoIzq].primero) < 0) {
                    swap(ihijoIzq, mod);
                } else {
                    sigoBajando = false;
                }
            } else if (tieneHijos(mod) == 2 && (heapArray[mod].primero.compareTo(heapArray[ihijoIzq].primero) < 0
                    || heapArray[mod].primero.compareTo(heapArray[ihijoDer].primero) < 0)) {
                int hijoMayor = heapArray[ihijoIzq].primero.compareTo(heapArray[ihijoDer].primero);
                if (hijoMayor == 1) { // N hijo izquiero es el mayor
                    swap(ihijoIzq, mod);
                    mod = ihijoIzq;
                    ihijoIzq = (2 * mod) + 1;
                    ihijoDer = (2 * mod) + 2;
                } else { // N hijo derecho es el mayor
                    swap(ihijoDer, mod);
                    mod = ihijoDer;
                    ihijoIzq = (2 * mod) + 1;
                    ihijoDer = (2 * mod) + 2;
                }
            } else {
                sigoBajando = false;
            }
        }

    }

    private void heapifyUp(int n) {
        int mod = n;
        int iPadre = (n - 1) / 2;
        while (heapArray[mod].primero.compareTo(heapArray[iPadre].primero) > 0 && mod != 0) { // O(Log n)
            swap(mod, iPadre);
            mod = iPadre;
            iPadre = (mod - 1) / 2;
        }
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

    private void swap(int Sube, int Baja) {
        Tupla<T> padreAnterior = heapArray[Sube];
        heapArray[Sube] = heapArray[Baja];
        heapArray[Baja] = padreAnterior;

        handleArray[heapArray[Sube].segundo] = Sube; //ver
        handleArray[heapArray[Baja].segundo] = Baja;

    }

    public int obtenerPosicion(int id) { //O(1)
        return handleArray[id];
    }

    public T maximo() {
        return heapArray[0].primero;
    }

    public void desencolar() {
        int Raiz = 0;
        int handleOrig = heapArray[Raiz].segundo;
        Tupla<T> Ultimo = heapArray[tamaño - 1];

        heapArray[Raiz] = Ultimo;
        heapArray[tamaño - 1] = null;

        handleArray[handleOrig] = -1; //estaba actualizando mal este handle
        handleArray[Ultimo.segundo] = 0;

        tamaño -= 1;
        heapifyDown(Raiz);
    }

}
