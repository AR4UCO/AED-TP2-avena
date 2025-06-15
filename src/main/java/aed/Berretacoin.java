package aed;

public class Berretacoin {
    private Saldos saldo;
    private BlockChain BlockChain;

    public Berretacoin(int n_usuarios) { // O(P)
        saldo = new Saldos(n_usuarios);
        BlockChain = new BlockChain();
    }

    public void agregarBloque(Transaccion[] transacciones) { // O(n*log(p))

        for (Transaccion t : transacciones) {
            if (t.id_comprador() == 0) {
            } else {
                saldo.actualizar(t.id_comprador(), (-1 * t.monto()));
            }
            saldo.actualizar(t.id_vendedor(), t.monto());
        }
        Bloque bloque = new Bloque(transacciones);

        BlockChain.agregarAtras(bloque);
    }

    public Transaccion txMayorValorUltimoBloque() { // O(1)
        return BlockChain.ultimo().mayorValor();
    }

    public Transaccion[] txUltimoBloque() { // O(n)
        return BlockChain.ultimo().bloquexId();
    }

    public int maximoTenedor() { // O(1)
        return saldo.maximoTenedor();
    }

    public int montoMedioUltimoBloque() { // O(1)
        Bloque ultimo = BlockChain.ultimo();
        if (ultimo.tieneCreacion() == true) {
            if (ultimo.longitud() == 1) {
                return 0;
            } else {
                return (ultimo.montoTotal() / (ultimo.longitud() - 1));
            }
        } else {
            return (ultimo.montoTotal() / ultimo.longitud());
        }
    }

    public void hackearTx() { // O(log(n) + log(p))
        Bloque ultimo = BlockChain.ultimo();
        Transaccion hackeada = ultimo.mayorValor();

        if (hackeada.id_comprador() == 0) {
        } else {
            saldo.actualizar(hackeada.id_comprador(), hackeada.monto());
        }
        saldo.actualizar(hackeada.id_vendedor(), (-1 * hackeada.monto()));

        ultimo.eliminarMaximo();
    }

    private Bloque ultimoBloque() {
        Bloque ultimo = BlockChain.obtener(BlockChain.longitud() - 1);
        return ultimo;
    }
}
