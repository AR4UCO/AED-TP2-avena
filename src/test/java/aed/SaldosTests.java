package aed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class SaldosTests {

    @Test
    void actualizarAumentaSaldo() {
        Saldos s = new Saldos(5);
        assertEquals(1, s.maximoTenedor());
        s.actualizar(4, 17);
        assertEquals(4, s.maximoTenedor());
        s.actualizar(2, 25);
        assertEquals(2, s.maximoTenedor());

    }

    @Test
    void actualizarDisminuyeSaldo() {
        Saldos s = new Saldos(5);
        assertEquals(1, s.maximoTenedor());
        s.actualizar(4, 17);
        assertEquals(4, s.maximoTenedor());
        s.actualizar(2, 15);
        s.actualizar(4, -10);
        assertEquals(2, s.maximoTenedor());

    }

    @Test
    void desempatePorId() {
        Saldos s = new Saldos(5);
        s.actualizar(2, 10);
        s.actualizar(5, 10);

        assertEquals(2, s.maximoTenedor());

    }
}
