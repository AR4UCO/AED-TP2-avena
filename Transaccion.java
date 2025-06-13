package aed;

public class Transaccion implements Comparable<Transaccion> {
    private int id;
    private int id_comprador;
    private int id_vendedor;
    private int monto;

    public Transaccion(int id, int id_comprador, int id_vendedor, int monto) {
        this.id = id;
        this.id_comprador = id_comprador;
        this.id_vendedor = id_vendedor;
        this.monto = monto;
    }

    @Override
    // Todos los tipos de datos "Comparables" tienen el método compareTo()
    // elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
    public int compareTo(Transaccion otro) {
        if (this.monto == otro.monto()) {
            if (this.id > otro.id) {
                return 1;
            }else if (this.id < otro.id) {
                return -1;
            } else {
                return 0;
            }
        }else if (this.monto > otro.monto()) {
            return 1;
        }else return -1;
    }

    @Override
    public boolean equals(Object otro){
        boolean res = false;
        if(otro.getClass() == this.getClass()){
            Transaccion otroT = (Transaccion) otro;
            res = (otroT.id()==this.id && otroT.id_comprador() == this.id_comprador && otroT.id_vendedor() == this.id_vendedor && otroT.monto() == this.monto);
        }
        return res;
    }

    public int monto() {
        return monto;
    }

    public int id_comprador() {
        return id_comprador;
    }
    
    public int id_vendedor() {
        return id_vendedor;
    }

    public int id() {
        return id;
    }
}