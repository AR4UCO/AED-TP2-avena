package aed; 

public class ColaPrioridad<T extends Comparable<T>>{
    private T[] heapArray;
    private elemhandle[] handleArray;
    private int tamaño;
    private T corrimiento;

    private class elemhandle {
        private T valor;
        private int posicion;
        elemhandle(t)
        
    }


    public ColaPrioridad(T[] base) {
        heapArray = base.clone(); 
        tamaño = base.length;
        if (tamaño != 0) {  //O(1)
            corrimiento = base[0];
            int i = 0;
            for (T b : base) {
                elemhandle a = new elemhandle(); 
                handleArray[i] = ;
                i++;
            }  
            int n = tamaño - 1;
            while (n >= 0) {
                heapifyDown(n);
                n -= 1;
            }
        }
    }

    public void actualizar(int handle, int subeObaja) { //posicion y mayor o menor a 0
        int i = handleArray[handle];

        if (subeObaja > 0) {
            heapifyUp(i);
        } else if (subeObaja < 0) {
            heapifyDown(i);
        }
    }



    public int size() {
        return tamaño;
    }

    private void heapifyDown(int n) {
            boolean sigoBajando = true;
            int ihijoIzq = (2 * n) + 1;
            int ihijoDer = (2 * n) + 2;
            int mod = n;
            while (tieneHijos(mod) != 0 && sigoBajando) { // O(Log n)
                if (tieneHijos(mod) == 1) {
                    if (heapArray[mod].compareTo(heapArray[ihijoIzq]) < 0) {
                        swap(ihijoIzq, mod);
                    } else {
                        sigoBajando = false;
                    }
                } else if (tieneHijos(mod) == 2 && (heapArray[mod].compareTo(heapArray[ihijoIzq]) < 0
                        || heapArray[mod].compareTo(heapArray[ihijoDer]) < 0)) {
                    int hijoMayor = heapArray[ihijoIzq].compareTo(heapArray[ihijoDer]);
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
        while (heapArray[mod].compareTo(heapArray[iPadre]) > 0 && mod != 0) { // O(Log n)
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

    private void swap(T Sube, int Baja) {
        T padreAnterior = heapArray[Baja];
        heapArray[Baja] = heapArray[Sube];
        heapArray[Sube] = padreAnterior;

        handleArray[heapArray[Baja]] = Sube;
        handleArray[heapArray[Sube]] = Baja;
    }

    public T maximo() {
        return heapArray[0];
    }

    public void desencolar() {
        T Raiz = heapArray[0];
        T Ultimo = heapArray[tamaño - 1];

        handleArray[Raiz - corrimiento] = -1;
        handleArray[Ultimo - corrimiento] = 0;

        heapArray[0] = Ultimo;
        heapArray[tamaño - 1] = null;

        tamaño -= 1;
        heapifyDown(0);
    }



}