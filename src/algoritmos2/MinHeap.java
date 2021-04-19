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
public class MinHeap {
    private int[] elementos;
    private int largo;
    private int ultimoLibre;
    
    
    public MinHeap(int tamano){
        elementos=new int[tamano+1];
        largo=tamano;
        ultimoLibre=1;
    }
    
  
    //para navegar hacia la izquierda
    public int izq(int posNodo){
        return posNodo*2;
    }
    //para navegar hacia la derecha
    public int der(int posNodo){
        return posNodo*2 +1;
    }
    
    //para navegar hacia mi padre
    public int padre(int posNodo){
        return posNodo/2;
    }
    
    //intercambiar nodos 
    public void intercambiar (int x, int y){
        int aux=elementos[x];
        elementos[x]=elementos[y];
        elementos[y]=aux;
    }
    
    public void insertar(int elemento){
        if(!estaLleno()){
        //inserto nuevo elemento en ultima posicion libre
        elementos[ultimoLibre]=elemento;
        //floto ultima posicion
        flotar(ultimoLibre);
        ultimoLibre++;
        }
    }
    
    private void flotar(int posNodo){
        if(posNodo!=1){
            int posNodoPadre=padre(posNodo);
            if(elementos[posNodo]<elementos[posNodoPadre]){
                intercambiar(posNodo, posNodoPadre);
                flotar(posNodoPadre);
             }
        }
    }
    
    public int obtenerMinimo(){
        if(!esVacio()) return elementos[1];
        return -1;
    }
    
    public void borrarMinimo(){
        if(!esVacio()){
            //por que es ultimo libre -1?
            elementos[1]=elementos[ultimoLibre-1];
            ultimoLibre--;
            hundir(1);
        }
    }
    
    private void hundir(int posNodo){
        if (izq(posNodo)<=ultimoLibre){
            int posIzq=izq(posNodo);
            int posDer=der(posNodo);
            int hijoMenor=posIzq;
            
            if(posDer<ultimoLibre && elementos[posDer]<elementos[posIzq]){
                hijoMenor=posDer;
            }
            if (elementos[hijoMenor]<elementos[posNodo]){
                intercambiar(hijoMenor, posNodo);
                hundir(hijoMenor);
            }
        }
    }
    
    public boolean esVacio(){
        return ultimoLibre==1;
    }
    
    public boolean estaLleno(){
        return ultimoLibre>largo;
    }
    
    
    public void imprimirHeap() {
        System.out.println("Array: ");
        for(int i=1; i < ultimoLibre; i++) {
            System.out.print(elementos[i] + " ");
        }
        System.out.println("");
        System.out.println("Arbol:");
        int cantidadPorNivel = 1;
        int impresosDelNivel = 0;
        for(int i=1; i < ultimoLibre; i++) {
            System.out.print(elementos[i] + " ");
            impresosDelNivel++;
            if(cantidadPorNivel == impresosDelNivel) {
                System.out.println("");
                cantidadPorNivel *= 2;
                impresosDelNivel = 0;
            }
        }
		System.out.println("\n");
    }
}
