package aed;

public class Berretacoin {

    private Saldos saldo;
    private BlockChain<Bloque<Transaccion>> BlockChain;


    public Berretacoin(int n_usuarios){
        saldo = new Saldos(n_usuarios);
        BlockChain = new BlockChain();
    }

    public void agregarBloque(Transaccion[] transacciones){

        for (Transaccion T : transacciones) {
            saldo.actualizar(T.id_comprador(), (-1 * T.monto()));
            saldo.actualizar(T.id_vendedor(), T.monto());
        }

        // falta hacer la parte de la blockchain
    }

    public Transaccion txMayorValorUltimoBloque(){
        throw new UnsupportedOperationException("Implementar!");
    }

    public Transaccion[] txUltimoBloque(){
        throw new UnsupportedOperationException("Implementar!");
    }

    public int maximoTenedor(){
        return saldo.maximoTenedor();
    }

    public int montoMedioUltimoBloque(){
        throw new UnsupportedOperationException("Implementar!");
    }

    public void hackearTx(){
        throw new UnsupportedOperationException("Implementar!");
    }
}
