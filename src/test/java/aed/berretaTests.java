package aed;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

import jdk.jfr.Timestamp;


class BerretaCoinPropios {



    //id,id_C,id_V,monto


    @Test
    public void caso1(){  //Agrego 2 bloques. Chequeo MAXTenedor/MayUltBloque
        
        Berretacoin berretacoin = new Berretacoin(3);

        Transaccion[] bloque1 = {
            new Transaccion(0,0,2,1), //C. el 2 tiene $1
            new Transaccion(1,2,1,1) // 1 = $1
        };
        
        berretacoin.agregarBloque(bloque1);
        assertEquals(2, berretacoin.maximoTenedor());
        
        Transaccion[]  bloque2 = {
            new Transaccion(0,0,2,2), //C. el 2 tiene $2
            new Transaccion(1,1,3,1), // 3 = $1
            new Transaccion(2,2,3,2)  // 3 = $2
        };  //ACA el 1 deberia tener 0, el 2 deberia tener 0, y el 3 deberia tener 3
        
        berretacoin.agregarBloque(bloque2);

        assertEquals(3, berretacoin.maximoTenedor());
        assertEquals(2, berretacoin.txMayorValorUltimoBloque());

        berretacoin.hackearTx();

        assertEquals(0, berretacoin.txMayorValorUltimoBloque());
        assertTrue(Arrays.equals((Arrays.copyOfRange(bloque, 0, bloque.length - 1)), berretacoin.txUltimoBloque()));

    }

    @Test 
    public void caso2(){ // 3 usuarios con mismo saldo

        Berretacoin bc = new Berretacoin(6);
        
        Transaccion[] bloque1 = {
            new Transaccion(0,0,4,1), //C. 4=$1
            new Transaccion(1,4,1,1) //  1 = $1, 4= 0  
        };

        bc.agregarBloque(bloque1);
        
        Transaccion[] bloque2 = {
            new Transaccion(0,0,3,2), //C. 3= 2 , 1 = 1
            new Transaccion(1,1,6,1), //  6=1
            new Transaccion(2,3,6,2), //  6 = 3  
            new Transaccion(3,6,5,3)  // 5 = 3
        };

        bc.agregarBloque(bloque2);
        assertEquals(5, bc.maximoTenedor());

        bc.hackearTx();

        assertEquals(6, bc.maximoTenedor());


        Transaccion[] bloque3 = {
            new Transaccion(0,0,4,3), // 4 = 3
        };

        bc.agregarBloque(bloque3);

        Transaccion[] bloque4 = {
            new Transaccion(0,0,2,4), //C. 2=4
            new Transaccion(1,2,3,3), //   3 = 3
            new Transaccion(2,4,5,3), //    5  = 3 
            new Transaccion(3,3,1,3) // 1 = 3
 
        };
      
        bc.agregarBloque(bloque4);
        
        // 1 = $3, 6 = $3, 5 = $3, 2 = $1
        
        assertEquals(1, bc.maximoTenedor());
        bc.hackearTx();

        // 3 = $3, 6 = $3, 5 = $3, 2 = $1+

        assertEquals(3, bc.maximoTenedor());
        
        

    }

    @Test 
    public void hackeo_Y_vacio(){
        Berretacoin bc = new Berretacoin(3);
        
        Transaccion[] bloque1 = {
            new Transaccion(0,0,2,1), // 2 = 1
            new Transaccion(1,2,1,1), // 1 = 1
            new Transaccion(2,1,3,1), // 3 = 1
            new Transaccion(3,3,1,1) // 1 = 1        
        };

        bc.agregarBloque(bloque1);
        bc.hackearTx();
        bc.hackearTx();
        bc.hackearTx();
        bc.hackearTx();
        assertEquals(1, bc.maximoTenedor());
        assertTrue(Arrays.equals((Arrays.copyOfRange(bloque, 0, bloque.length - 4)), berretacoin.txUltimoBloque()));
        
    }

    @Test 
    public void montosMedio(){

        Berretacoin bc = new Berretacoin(6);
        
        Transaccion[] bloque1 = {
            new Transaccion(0,0,4,1), //C. 4=$1
            new Transaccion(1,4,1,1) //  1 = $1, 4= 0  
        };

        bc.agregarBloque(bloque1);
        
        Transaccion[] bloque2 = {
            new Transaccion(0,0,3,2), //C. 3= 2 , 1 = 1
            new Transaccion(1,1,6,1), //  6=1
            new Transaccion(2,3,6,2), //  6 = 3  
            new Transaccion(3,6,5,3)  // 5 = 3
        };

        bc.agregarBloque(bloque2);
        assertEquals(2, bc.montoMedioUltimoBloque());


        Transaccion[] bloque3 = {
            new Transaccion(0,0,4,3), // 4 = 3
        };

        bc.agregarBloque(bloque3);
        assertEquals(0, bc.montoMedioUltimoBloque());

        Transaccion[] bloque4 = {
            new Transaccion(0,0,2,4), //C. 2=4
            new Transaccion(1,2,3,3), //   3 = 3
            new Transaccion(2,4,6,3), //    6  = 3 
            new Transaccion(3,3,1,3), // 1 = 3
            new Transaccion(4,2,4,1)
        };
      
        bc.agregarBloque(bloque4);
        
        // 1 = $3, 6 = $3, 5 = $3, 4 = $1
        
        assertEquals(2, bc.montoMedioUltimoBloque());
        bc.hackearTx();
        assertEquals(3, bc.montoMedioUltimoBloque());


    }


}





