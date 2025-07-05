package aed;

public class ListaDoblementeEnlazada<T> implements Secuencia<T> {
    private Nodo primero;
    private Nodo ultimo;
    private int longi;

    private class Nodo {
        T valor;
        Nodo sig;
        Nodo ant;

        Nodo(T v) {
            valor = v;
        }
    }

    public ListaDoblementeEnlazada() {
        primero = null;
        ultimo = null;
        longi = 0;
    }

    public int longitud() {
        return longi;
    }

    public T ultimo() {
        return ultimo.valor;
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (ultimo == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            nuevo.ant = ultimo;
            ultimo.sig = nuevo;
            ultimo = nuevo;
        }
        longi += 1;
    }

    public T obtener(int i) {
        Nodo actual = primero;
        for (int j = 0; j < i; j++) {
            actual = actual.sig;
        }
        return actual.valor;
    }

    private class ListaIterador implements Iterador<T> {
        int contador;

        ListaIterador() {
            contador = 0;
        }

        public boolean haySiguiente() {
            return contador != longi;

        }

        public boolean hayAnterior() {
            return contador != 0;
        }

        public T siguiente() {
            Nodo actual = primero;
            int l = 0;
            while (l != contador) {
                actual = actual.sig;
                l += 1;
            }

            contador = contador + 1;
            return actual.valor;

        }

        public T anterior() {
            Nodo actual = ultimo;
            int l = longi;
            while (l != contador) {
                actual = actual.ant;
                l = l - 1;
            }
            contador = contador - 1;
            return actual.valor;
        }
    }

    public Iterador<T> iterador() {
        return new ListaIterador();
    }

}
