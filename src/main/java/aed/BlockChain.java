package aed;

public class BlockChain implements Secuencia<Bloque> {
    private Nodo primero;
    private Nodo ultimo;
    private int longi;

    private class Nodo {
        Bloque valor;
        Nodo sig;
        Nodo ant;

        Nodo(Bloque v) {valor = v;}
    }

    public BlockChain() {
        primero = null;
        ultimo = null;
        longi = 0;
    }

    public int longitud() {
        return longi;
    }

    public Bloque ultimo() {
        return ultimo.valor;
    }

    public void agregarAtras(Bloque elem) {
        Nodo nuevo = new Nodo(elem);
        if (ultimo == null){
            primero = nuevo;
            ultimo = nuevo;
        } else{
            nuevo.ant = ultimo;
            ultimo.sig = nuevo;
            ultimo = nuevo;
        }
        longi +=1;
    }

    public Bloque obtener(int i) {
        Nodo actual = primero;
        for (int j=0;j<i;j++){
            actual = actual.sig;
        }
        return actual.valor;
    }

    private class ListaIterador implements Iterador<Bloque> {
    	int contador;

        ListaIterador(){
            contador = 0;
        }

        public boolean haySiguiente() {
            return contador != longi;

        }

        public boolean hayAnterior() {
            return contador != 0;
        }

        public Bloque siguiente() {
            Nodo actual = primero;
            int l = 0;
            while (l != contador){
                actual = actual.sig;
                l+=1;
            }

            contador = contador +1;
            return actual.valor;

        }


        public Bloque anterior() {
            Nodo actual = ultimo;
            int l = longi;
            while(l != contador){
                actual = actual.ant;
                l = l -1;
            }
            contador = contador -1;
            return actual.valor;
        }
    }

    public Iterador<Bloque> iterador() {
	    return new ListaIterador();
    }

}
