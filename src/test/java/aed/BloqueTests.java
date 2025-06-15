package aed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class BloqueTests {

    @Test
    void BloqueVacio() {
        Transaccion[] bloque1 = {};
        Bloque bloque = new Bloque(bloque1);
        assertEquals(0, bloque.longitud());
        assertEquals(0, bloque.montoTotal());
    }

    @Test
    void transaccionDeCreacion() {
        Transaccion[] bloque1 = {
                new Transaccion(0, 0, 2, 1),
                new Transaccion(1, 2, 1, 1) };
        Bloque bloque = new Bloque(bloque1);
        assertTrue(bloque.tieneCreacion());
    }

    @Test
    void montoTotalTest() {
        Transaccion[] bloque1 = {
                new Transaccion(1, 30, 102, 2000),
                new Transaccion(2, 103, 107, 1500),
                new Transaccion(3, 108, 109, 200)
        };
        Bloque bloque = new Bloque(bloque1);
        assertEquals(3700, bloque.montoTotal());
    }

    @Test
    void sumaNoCreacion() {
        Transaccion[] bloque1 = {
                new Transaccion(1, 0, 102, 2000),
                new Transaccion(2, 103, 107, 1500),
                new Transaccion(3, 108, 109, 200)
        };
        Bloque bloque = new Bloque(bloque1);
        assertEquals(1700, bloque.montoTotal());
    }

    @Test
    void longitudTest() {
        Transaccion[] bloque1 = {
                new Transaccion(1, 40, 98, 4),
                new Transaccion(2, 77, 100, 9),
                new Transaccion(3, 20, 35, 10)
        };
        Bloque bloque = new Bloque(bloque1);
        assertEquals(3, bloque.longitud());
    }

    @Test
    void mayorValorTest() {
        Transaccion[] bloque1 = {
                new Transaccion(1, 40, 98, 4),
                new Transaccion(2, 77, 100, 15),
                new Transaccion(3, 20, 35, 10)
        };
        Bloque bloque = new Bloque(bloque1);
        Transaccion t = new Transaccion(2, 77, 100, 15);
        assertEquals(t, bloque.mayorValor());
    }

    @Test
    void eliminarMaxTest() {
        Transaccion[] bloque1 = {
                new Transaccion(1, 40, 98, 4),
                new Transaccion(2, 77, 100, 15),
                new Transaccion(3, 20, 35, 10)
        };
        Bloque bloque = new Bloque(bloque1);
        bloque.eliminarMaximo();
        assertEquals(10, bloque.mayorValor().monto());
        assertEquals(2, bloque.longitud());
        assertEquals(14, bloque.montoTotal());
    }

}
