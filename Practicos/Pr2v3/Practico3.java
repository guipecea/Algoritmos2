import java.util.Scanner;

abstract class ColaDePrioridad {
    abstract void insertar(int elemento);
    abstract void desencolar();
    abstract int tope();
    abstract boolean esVacia();
    abstract boolean estaLlena();
};

class MinHeapElement{
    String instr;
    int prio;

    public MinHeapElement(String instruccion, int prioridad){
        instr = instruccion;
        prio = prioridad;
    }
    
    public String getInstruccion(){
        return instr;
    }
    
    public int getPrioridad(){
        return prio;
    }
}

class MinHeap {
    private MinHeapElement[] elementos;
    private int largo;
    private int ultimoLibre;

    public MinHeap(int tamanio) {
        elementos = new MinHeapElement[tamanio + 1];
        largo = tamanio;
        ultimoLibre = 1;
    }

    private int izq(int nodo) {
        return nodo * 2;
    }

    private int der(int nodo) {
        return nodo * 2 + 1;
    }

    private int padre(int nodo) {
        return nodo/2;
    }

    private void intercambiar(int x, int y) {
        int aux = elementos[x];
        elementos[x] = elementos[y];
        elementos[y] = aux;
    }

    public void insertar(int nuevoElemento) {
        if(!estaLleno()) {
            elementos[ultimoLibre] = nuevoElemento;
            flotar(ultimoLibre);
            ultimoLibre++;
        }
    }

    private void flotar(int nodo) {
        if(nodo != 1) {
            int nodoPadre = padre(nodo);
            if(elementos[nodo].getPrioridad() < elementos[nodoPadre].getPrioridad()) {
                intercambiar(nodo, nodoPadre);
                flotar(nodoPadre);
            }
        }
    }

    public int obtenerMinimo() {
        if(!esVacio()) {
            return elementos[1];
        }
        return -1;
    }

    public void borrarMinimo() {
        if(!esVacio()) {
            elementos[1] = elementos[ultimoLibre - 1];
            ultimoLibre--;
            hundir(1);
        }
    }

    private void hundir(int nodo) {
        // si tiene hijos (al menos 1)
        if(izq(nodo) < ultimoLibre) {
            int izq = izq(nodo);
            int der = der(nodo);
            int hijoMenor = izq;

            //De mis 2 hijos, me fijo quien tiene menor prioridad.
            if(der < ultimoLibre && elementos[der].getPrioridad() < elementos[izq].getPrioridad()) {
                hijoMenor = der;
            }

            if(elementos[hijoMenor].getPrioridad() < elementos[nodo].getPrioridad()) {
                intercambiar(hijoMenor, nodo);
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

}

class ColadePrioridadImpHeap extends ColaDePrioridad{
    private MinHeap miHeap;

    public ColaDePrioridadImpHeap(int tamanio) {
        miHeap = new MinHeap(tamanio);
    }

    public void insertar(int el) {
        miHeap.insertar(el);
    }
    
    public void desencolar() {
        miHeap.borrarMinimo();
    }
    
    public int tope() {
		return miHeap.obtenerMinimo();
	}
    
    public boolean esVacia() {
        return miHeap.esVacio();
    }
    
    public boolean estaLlena() {
        return miHeap.estaLleno();
    }
}

class Practico3 {
    public static void main (String[] args){

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        System.out.println("N vale: " + n);        
        System.out.println("Primer comando: " + sc.nextLine());                
        for (int i = 0; i < n; i++) {
            // vec[i] = sc.nextInt();
            //tree.root = tree.insert(tree.root,sc.nextInt());
            System.out.println("Sig Commando: " + sc.nextLine());                
        }

        sc.close();
        //sort(vec, n);
        //tree.preOrder(tree.root);
    }
}
