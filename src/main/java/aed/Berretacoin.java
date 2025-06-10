package aed;

public class Berretacoin {

    private Saldos saldo;
    private BlockChain<Bloque> BlockChain;


    public Berretacoin(int n_usuarios){
        saldo = new Saldos(n_usuarios);
        BlockChain = new BlockChain<Bloque>();
    }

    public void agregarBloque(Transaccion[] transacciones){

        for (Transaccion T : transacciones) {
            if (T.id_comprador() == 0) {
            } else {
                saldo.actualizar(T.id_comprador(), (-1 * T.monto()));
            }
            saldo.actualizar(T.id_vendedor(), T.monto());
        }
        Bloque bloque = new Bloque(transacciones);

        BlockChain.agregarAtras(bloque);
    }

    public Transaccion txMayorValorUltimoBloque(){
        return ultimoBloque().mayorValor();
    }

    public Transaccion[] txUltimoBloque(){
        throw new UnsupportedOperationException("Implementar!");
    }

    public int maximoTenedor(){
        return saldo.maximoTenedor();
    }

    public int montoMedioUltimoBloque(){
        return(ultimoBloque().montoTotal()/ultimoBloque().longitud());
        //N podriamos agregar un metodo que calcule el total sobre la longitud (osea el promedio) asi solo llamariamos a ese metodo
        //N (hay que mantener los metodos individuales de todos modos por que son necesarios para hackearTx)
    }

    public void hackearTx(){
        throw new UnsupportedOperationException("Implementar!");
    }

    private Bloque ultimoBloque() {
        Bloque ultimo = BlockChain.obtener(BlockChain.longitud()-1);
        return ultimo;
        //N podria posiblemente simplificarse si hacemos que obtener obtenga siempre el ultimo en cuyo caso creo no se necesita el metodo longitud
        //N (considerar pros y contras)
        //N el codigo podria quedar simplemente: 
        //N return BlockChain.ultimo();
    }
}


