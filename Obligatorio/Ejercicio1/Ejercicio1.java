import java.util.Scanner;

//Implementacion Utilizando MinHeap
class MinHeap {
    private int[] elementos;
    private int largo;
    private int ultimoLibre;

    public MinHeap(int tamanio) {
        elementos = new int[tamanio + 1];
        largo = tamanio;
        ultimoLibre = 1;
    }

    // navegar hacia la izquierda
    private int izq(int posNodo) {
        return posNodo * 2;
    }
    
    // navegar hacia la derecha
    private int der(int posNodo) {
        return posNodo * 2 + 1;
    }

    // navego hacia mi padre
    private int padre(int posNodo) {
        return posNodo/2;
    }

    private void intercambiar(int x, int y) {
        int aux = elementos[x];
        elementos[x] = elementos[y];
        elementos[y] = aux;
    }
    // O Log(n) PC | O 1 CP
    public void insertar(int nuevoElemento) {
        if(!estaLleno()) {
            // inserto en la ultima posicion libre
            elementos[ultimoLibre] = nuevoElemento;
            // floto la ultima posicion libre
            flotar(ultimoLibre);
            ultimoLibre++;
        }
    }

    private void flotar(int posNodo) {
        // si no llegue a la raiz
        if(posNodo != 1) {
            int posNodoPadre = padre(posNodo);
            // en el caso de que no sea mi posicion: intercambio y sigo flotando
            if(elementos[posNodo] < elementos[posNodoPadre]) {
                intercambiar(posNodo, posNodoPadre);
                flotar(posNodoPadre);
            }
        }
    }
    // O 1 CP | O 1 PC
    public int obtenerMinimo() {
        if(!esVacio()) {
            return elementos[1];
        }
        return -1;
    }
    //O Log(n) CP || O Log(n) PC
    public void borrarMinimo() {
        if(!esVacio()) {
            // pongo en la raiz el ultimo elemento
            elementos[1] = elementos[ultimoLibre - 1];
            ultimoLibre--;
            // hundo la raiz
            hundir(1);
        }
    }

    private void hundir(int posNodo) {
        // si tiene hijos (al menos 1)
        if(izq(posNodo) < ultimoLibre) {
            int posIzq = izq(posNodo);
            int posDer = der(posNodo);
            int hijoMenor = posIzq;

            // si tengo hijo derecho && el hijo derecho es menor que el hijo izquierdo
            if(posDer < ultimoLibre && elementos[posDer] < elementos[posIzq]) {
                hijoMenor = posDer;
            }

            if(elementos[hijoMenor] < elementos[posNodo]) {
                intercambiar(hijoMenor, posNodo);
                hundir(hijoMenor);
            }
        }
    }

    public boolean esVacio() {
        return ultimoLibre == 1;
    }

    public boolean estaLleno() {
        return ultimoLibre > largo;
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

public class Ejercicio1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        MinHeap heap = new MinHeap(n);

        //n elementos a insertar --> n
        //insertar --> O Log(n) PC | O 1 CP
        //O n*Log(n) 
        for (int i = 0; i < n; i++) {
            
            heap.insertar(sc.nextInt());

        }
        sc.close();
        //n elementos en el heap --> n
        //obtener minimo --> O 1 siempre
        //imprimirMinimo --> O 1 siempre
        //borrarMinimo --> Log(n) siempre
        // O n*Log(n)        
        while(!heap.esVacio()){
            
            System.out.println(heap.obtenerMinimo());
            heap.borrarMinimo();

        }

        //Orden total = Max (O(for), O(while)) = n*Log(n)  
        
    }

}