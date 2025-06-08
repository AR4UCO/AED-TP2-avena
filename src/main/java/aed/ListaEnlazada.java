package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo primero;
    private Nodo ultimo;
    private int longi;

    private class Nodo {
        T valor;
        Nodo sig;
        Nodo ant;

        Nodo(T v) {valor = v;}
    }

    public ListaEnlazada() {
        primero = null;
        ultimo = null;
        longi = 0;
    }


    public ListaEnlazada(ListaEnlazada<T> lista) {
        Nodo actual = lista.primero;
        while (actual != null){
            agregarAtras(actual.valor);
            actual = actual.sig;
        }
    }


    public int longitud() {
        return longi;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (primero == null){
            ultimo = nuevo;
            primero = nuevo;
        } else {
            nuevo.sig = primero;
            primero.ant =nuevo;
            primero = nuevo;
        }
        longi +=1;
    }

    public void agregarAtras(T elem) {
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

    public T obtener(int i) {
        Nodo actual = primero;
        for (int j=0;j<i;j++){
            actual = actual.sig;
        }
        return actual.valor;
    }

    public void eliminar(int i) {
        Nodo actual = primero;
        for (int j=0;j<longi;j++){
            if (j == i){
                if (primero == ultimo){
                    primero = null;
                    ultimo = null;
                }else if (actual == primero){
                    primero = actual.sig;
                    actual.sig.ant = null;
                } else if(actual == ultimo){
                    ultimo = actual.ant;
                    actual.ant.sig = null;
                } else{
                actual.ant.sig = actual.sig;
                actual.sig.ant = actual.ant;}
                longi -=1;
            }
            actual = actual.sig;
        }
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo actual = primero;
        for (int j=0;j<longi;j++){
            if (j == indice){
                actual.valor = elem;
            }
            actual = actual.sig;
        }
    }



    @Override
    public String toString() {
        Nodo act = primero.sig;
        String actual = "[" + primero.valor;
        for (int i=0;i<longi-1;i++){

            actual = actual + "," + " " + act.valor;
            act = act.sig;
        }
        return actual + "]";

    }

    private class ListaIterador implements Iterador<T> {
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

        public T siguiente() {
            Nodo actual = primero;
            int l = 0;
            while (l != contador){
                actual = actual.sig;
                l+=1;
            }

            contador = contador +1;
            return actual.valor;

        }


        public T anterior() {
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

    public Iterador<T> iterador() {
	    return new ListaIterador();
    }

}
