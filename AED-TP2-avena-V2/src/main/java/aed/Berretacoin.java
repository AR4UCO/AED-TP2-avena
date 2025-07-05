package aed;

public class Berretacoin {
    private ColaPrioridad<Usuario> saldo;
    private ListaDoblementeEnlazada<BloqueDeTransacciones> BlockChain;

    public Berretacoin(int n_usuarios) { // O(P)
        int contador = 1;
        Usuario[] base = new Usuario[n_usuarios];
        while (contador != n_usuarios+1) {
            base[contador-1] = new Usuario(contador);  
        }

        saldo = new ColaPrioridad<>(base); //O(P)
        BlockChain = new ListaDoblementeEnlazada<>(); //O(1)
    }
    /*
        Se mantiene la complejidad porque se crea un heap saldos iterando en la cantidad de usuarios P O(P)
    */

    public void agregarBloque(Transaccion[] transacciones) { // O(n*log(p))

        for (Transaccion t : transacciones) { //O(n)
            if (t.id_comprador() == 0) { //O(1)
            } else {
                saldo.actualizar(t.id_comprador(), (-1 * t.monto())); //O(log(n)) (maximo la altura del arbol)
            }
            saldo.actualizar(t.id_vendedor(), t.monto()); //O(log(n)) (maximo la altura del arbol)
        }
        BloqueDeTransacciones bloque = new BloqueDeTransacciones(transacciones); //O(n)  (bottom up, por algoritmo de floyd)

        BlockChain.agregarAtras(bloque); //O(1)
    }
    /*
        Actualizar saldos es O(2*n*log(p)) y armar el heap bloque es O(n).
        Por lo que la complejidad es  O(2*n*log(p)) + O(n) = O(n*log(p)).
    */

    public Transaccion txMayorValorUltimoBloque() { // O(1)
        return BlockChain.ultimo().mayorValor();
    }
    
    // Devuelve la raiz del heap Bloque O(1)


    public Transaccion[] txUltimoBloque() { // O(n)
        return BlockChain.ultimo().BloquexId();
    }
    /* 
        La copia del último bloque de transacciones tiene una complejidad de O(n), donde n representa la cantidad histórica de transacciones 
        (es decir, se mantiene después de aplicar hackearTx).
        Cualquier búsqueda lineal también tiene complejidad O(n) independientemente del valor actual de n, entonces
        la complejidad sigue siendo adecuada.
    */ 

    public int maximoTenedor() { // O(1)
        return saldo.maximo().getId();
    }

    //Devuelve la raiz del heap saldos = O(1)

    public int montoMedioUltimoBloque() { // O(1)
        BloqueDeTransacciones ultimo = BlockChain.ultimo(); //O(1)
        return ultimo.montoMedio();
    } 
    
    /*
        montoTotal y tamaño son atributos del bloque.
        El llamado a ultimoBloque O(1), revisar si es de Creacion O(1), modificar montoTotal es O(1)
        y la division entre montoTotal y tamaño,tambien es O(1).
        Por lo que la complejidad es constante, O(1)    
    */

    public void hackearTx() { // O(log(n) + log(p))
        BloqueDeTransacciones ultimo = BlockChain.ultimo(); //O(1)
        Transaccion hackeada = ultimo.mayorValor(); //O(1)

        if (hackeada.id_comprador() == 0) { //O(1)
        } else { 
            saldo.actualizar(hackeada.id_comprador(), hackeada.monto()); //O(log(n)) maximo altura del arbol
        }
        saldo.actualizar(hackeada.id_vendedor(), (-1 * hackeada.monto())); //O(log(n)) maximo altura del arbol

        ultimo.hackear(); //O(log(n))
    }
    /*
        Reordenar el heap bloque luego de eliminar la raiz nos lleva O(log(n))
        y reordenar el heap saldos, luego de actualizar los valores de dos id nos lleva O(2*log(P)) = O(log(P))
        Por lo que se mantiene la complejidad O(log(n) + log(p)).
    */
}

