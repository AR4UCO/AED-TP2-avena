package aed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class BlockChainTests {

    @Test
    void nuevaListaEstaVacia() {
        BlockChain lista = new BlockChain();

        assertEquals(0, lista.longitud());
    }

    @Test
    void agregarUnElementoAtras() {
        BlockChain lista = new BlockChain();
        Transaccion[] transacciones = new Transaccion[0];
        Bloque bloque = new Bloque(transacciones);
        lista.agregarAtras(bloque);

        assertEquals(1, lista.longitud());
        assertEquals(bloque, lista.obtener(0));
    }

    @Test
    void agregarVariosElementosAtras() {
        BlockChain lista = new BlockChain();
        Bloque b1 = new Bloque(new Transaccion[0]);
        Bloque b2 = new Bloque(new Transaccion[0]);
        Bloque b3 = new Bloque(new Transaccion[0]);

        lista.agregarAtras(b1);
        lista.agregarAtras(b2);
        lista.agregarAtras(b3);

        assertEquals(3, lista.longitud());
        assertEquals(b1, lista.obtener(0));
        assertEquals(b2, lista.obtener(1));
        assertEquals(b3, lista.obtener(2));
    }

    @Test
    void iteradorListaVacia() {
        BlockChain lista = new BlockChain();

        Iterador<Bloque> it = lista.iterador();

        assertFalse(it.haySiguiente());
        assertFalse(it.hayAnterior());
    }

    @Test
    void iteradorRecorreListaHaciaAdelante() {
        BlockChain lista = new BlockChain();
        Bloque b1 = new Bloque(new Transaccion[0]);
        Bloque b2 = new Bloque(new Transaccion[0]);
        Bloque b3 = new Bloque(new Transaccion[0]);

        lista.agregarAtras(b1);
        lista.agregarAtras(b2);
        lista.agregarAtras(b3);

        Iterador<Bloque> it = lista.iterador();

        assertTrue(it.haySiguiente());
        assertEquals(b1, it.siguiente());
        assertTrue(it.haySiguiente());
        assertEquals(b2, it.siguiente());
        assertTrue(it.haySiguiente());
        assertEquals(b3, it.siguiente());
        assertFalse(it.haySiguiente());
    }

    @Test
    void iteradorEsBidireccional() {
        BlockChain lista = new BlockChain();
        Bloque b1 = new Bloque(new Transaccion[0]);
        Bloque b2 = new Bloque(new Transaccion[0]);
        Bloque b3 = new Bloque(new Transaccion[0]);

        lista.agregarAtras(b1);
        lista.agregarAtras(b2);
        lista.agregarAtras(b3);

        Iterador<Bloque> it = lista.iterador();

        assertTrue(it.haySiguiente());
        assertFalse(it.hayAnterior());
        assertEquals(b1, it.siguiente());
        assertTrue(it.haySiguiente());
        assertEquals(b2, it.siguiente());
        assertTrue(it.hayAnterior());
        assertEquals(b2, it.anterior());
        assertTrue(it.hayAnterior());
        assertEquals(b1, it.anterior());
        assertFalse(it.hayAnterior());
        assertTrue(it.haySiguiente());
        assertEquals(b1, it.siguiente());
        assertTrue(it.hayAnterior());
        assertTrue(it.haySiguiente());
        assertEquals(b2, it.siguiente());
        assertTrue(it.hayAnterior());
        assertTrue(it.haySiguiente());
        assertEquals(b3, it.siguiente());
        assertFalse(it.haySiguiente());
        assertTrue(it.hayAnterior());
        assertEquals(b3, it.anterior());
        assertTrue(it.hayAnterior());
        assertEquals(b2, it.anterior());
    }

}