package aed;

public class Berretacoin {
    private Saldos saldo;
    private BlockChain BlockChain;

    public Berretacoin(int n_usuarios) { // O(P)
        saldo = new Saldos(n_usuarios);
        BlockChain = new BlockChain();
    }
    /*
        Se mantiene la complejidad porque se crea un heap saldos iterando en la cantidad de usuarios P O(P)
    */

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
    /*
        Actualizar saldos es O(2*n*log(p)) y armar el heap bloque es O(log(n)).
        Por lo que la complejidad es  O(2*n*log(p)) + O(log(n)) = O(n*log(p)).
    */

    public Transaccion txMayorValorUltimoBloque() { // O(1)
        return BlockChain.ultimo().mayorValor();
    }
    
    // Devuelve la raiz del heap Bloque O(1)


    public Transaccion[] txUltimoBloque() { // O(n)
        return BlockChain.ultimo().bloquexId();
    }
    /* 
        La copia del último bloque de transacciones tiene una complejidad de O(n), donde n representa la cantidad histórica de transacciones 
        (es decir, se mantiene después de aplicar hackearTx).
        Cualquier búsqueda lineal también tiene complejidad O(n) independientemente del valor actual de n, entonces
        la complejidad sigue siendo adecuada.
    */ 

    public int maximoTenedor() { // O(1)
        return saldo.maximoTenedor();
    }

    //Devuelve la raiz del heap saldos = O(1)

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
    /*
        montoTotal y tamaño son atributos del bloque.
        El llamado a ultimoBloque O(1), revisar si es de Creacion O(1), modificar montoTotal es O(1)
        y la division entre montoTotal y tamaño,tambien es O(1).
        Por lo que la complejidad es constante, O(1)    
    */

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
        Bloque ultimo = BlockChain.ultimo();
        return ultimo;
    }
}
