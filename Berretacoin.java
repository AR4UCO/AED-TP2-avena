package aed;

public class Berretacoin {
    private Saldos saldo;
    private BlockChain BlockChain;


    public Berretacoin(int n_usuarios){
        saldo = new Saldos(n_usuarios);
        BlockChain = new BlockChain();
    }

    public void agregarBloque(Transaccion[] transacciones){

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

    public Transaccion txMayorValorUltimoBloque(){
        return ultimoBloque().mayorValor();
    }

    public Transaccion[] txUltimoBloque(){
        return BlockChain.ultimo().bloquexId();
    }

    public int maximoTenedor(){
        return saldo.maximoTenedor();
    }

    public int montoMedioUltimoBloque(){
        Bloque ultimo = ultimoBloque();
        if (ultimo.tieneCreacion() == true) {
            if (ultimo.longitud() == 1) {
                return 0;
            } else {
                return (ultimo.montoTotal()/(ultimo.longitud()-1));
            }
        } else {
            return(ultimo.montoTotal()/ultimo.longitud());
        }
    }

    public void hackearTx(){
        Bloque ultimo = ultimoBloque();
        Transaccion hackeada = ultimo.mayorValor();

        if (hackeada.id_comprador() == 0) {
           } else {
               saldo.actualizar(hackeada.id_comprador(), hackeada.monto());
           }
           saldo.actualizar(hackeada.id_vendedor(),(-1*hackeada.monto()));
        
        ultimo.eliminarMaximo();
    }

    private Bloque ultimoBloque() {
        Bloque ultimo = BlockChain.obtener(BlockChain.longitud()-1);
        return ultimo;
        }
}


