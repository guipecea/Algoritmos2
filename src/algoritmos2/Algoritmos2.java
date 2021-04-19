/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos2;

/**
 *
 * @author crodrigurez
 */
public class Algoritmos2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                    
        MinHeap heap = new MinHeap(20);
        heap.insertar(5);
        heap.insertar(2);
        heap.insertar(3);
        heap.insertar(64);
        heap.insertar(34);
        heap.insertar(64);
        heap.insertar(23);
        heap.insertar(21);
        
        heap.imprimirHeap();
/*
        System.out.println("Vacio el heap:");
        while(!heap.esVacio()) {
            System.out.println(heap.obtenerMinimo());
			heap.borrarMinimo();
        }   */
        ejercicio1(heap);
    }
    
    static public void ejercicio1(MinHeap mh){
        while (!mh.esVacio()){
           System.out.println(mh.obtenerMinimo());
           mh.borrarMinimo();
        }

    }
    
}
