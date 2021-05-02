import java.util.Scanner;
//Implementamos utilizando el algoritmo de orden topológico.
//Aplica para grafos dirigidos y aciclicos (si tiene un ciclo, nos vamos a enterar).
//Se puede implementar el algoritmo utilizando lista de adyacencia o Matriz de adyacencia. Al ser un grafo disperso utilizaremos la primera.

class Arista{
    int origen;
    int destino;
    int peso;
    Arista(int o, int d, int p){ origen = o ; destino = d; peso = p;}
    Arista(int o, int d){ origen = o ; destino = d; peso = 0;}

    public int getOrigen(){
        return origen;
    }
    public int getDestino(){
        return destino;
    }
    public int getPeso(){
        return peso;
    }
    void setOrigen(int ori){
        origen = ori;
    }
    void setDestino(int des){
        destino = des;
    }
    void setPeso(int pe){
        peso = pe;
    }

}

class NodoLista {
        Arista dato;
        NodoLista sig;
        NodoLista(Arista a){ dato = a; sig = null;}
        NodoLista(Arista a, NodoLista sigNodo){dato = a; sig = sigNodo;}

        public Arista getDato(){
            return dato;
        }
        public NodoLista getSigDato(){
            return sig;
        }
        public void setDato(Arista dat){
            dato = dat;
        }
        public void setSigDato(NodoLista sigDato){
            sig = sigDato;
        }
}

class LinkedListAristas {
    NodoLista head; //head of list
    int cantElementos=0;
    //imprimir lista 
    public void printLinkedList(){
        //apuntamos n al head de la lista.
        NodoLista n = head;

        while (n != null){
          //recorremos la lista.  
            System.out.print( n.dato + " ");
            n = n.sig; //apuntamos al siguiente nodo de la lista.
        }

    }

    //agregar nodo al principio de la lista
    public void push (Arista new_Arista){
        //creamos el nuevo nodo.
        NodoLista newNode = new NodoLista(new_Arista);

        //Sig del nuevo nodo, apunta al valor del head.
        newNode.setSigDato(head);

        //head apunta al nuevo nodo.
        head = newNode;

        cantElementos++;
    }

    //agregar nodo despues de uno dado
    public void insertAfter(int new_data, NodoLista prev_node){

        //check del nodo previo si es null.
        // if (prev_node == null){
        //     System.out.println("El nodo indicado no puede ser null");
        //     return;
        // }
        
        // Node new_node = new Node(new_data);

        // new_node.next = prev_node.next;

        // prev_node.next = new_node;

    }

    public int getCantElementos(){
        return cantElementos;
    }
    //agregar nodo al final 
    public void append(int new_data){

        // NodoLista newNode = new NodoLista(new_data);

        // if (head == null){

        //     head = newNode;
        //     return;
        // }

        // newNode.next = null ;

        // NodoLista last = head;

        // while (last.next != null){
        //     last = last.next;            
        // }

        // last.next = newNode;

        // return;
    }
}

// A class to represent a queue
class Queue {
    int front, rear, size;
    int capacity;
    int array[];
 
    public Queue(int capacity)
    {
        this.capacity = capacity;
        front = this.size = 0;
        rear = capacity - 1;
        array = new int[this.capacity];
    }
 
    // Queue is full when size becomes
    // equal to the capacity
    boolean isFull(Queue queue)
    {
        return (queue.size == queue.capacity);
    }
 
    // Queue is empty when size is 0
    boolean isEmpty(Queue queue)
    {
        return (queue.size == 0);
    }
 
    // Method to add an item to the queue.
    // It changes rear and size
    void enqueue(int item)
    {
        if (isFull(this))
            return;
        this.rear = (this.rear + 1)
                    % this.capacity;
        this.array[this.rear] = item;
        this.size = this.size + 1;
        System.out.println(item
                           + " enqueued to queue");
    }
 
    // Method to remove an item from queue.
    // It changes front and size
    int dequeue()
    {
        if (isEmpty(this))
            return Integer.MIN_VALUE;
 
        int item = this.array[this.front];
        this.front = (this.front + 1)
                     % this.capacity;
        this.size = this.size - 1;
        return item;
    }
 
    // Method to get front of queue
    int front()
    {
        if (isEmpty(this))
            return Integer.MIN_VALUE;
 
        return this.array[this.front];
    }
 
    // Method to get rear of queue
    int rear()
    {
        if (isEmpty(this))
            return Integer.MIN_VALUE;
 
        return this.array[this.rear];
    }
}

abstract class Grafo{
    LinkedListAristas[] listaAdy;
    Grafo(int cantidadDeVertices){ 
        listaAdy = new LinkedListAristas[cantidadDeVertices+1];
    }    
    // pre:
    //  - v y w son vertices validos del grafo
    //  - no existe la arista v w
    // pos: agrega la arista v w con su respectivo peso al grafo
    abstract void aniadirArista(int v, int w , int p);
    // pre: el vertice origen es un vertice valido del grafo
    // pos: retorna una lista de Arsitas las cuales tienen como origen al vertice parametro
    abstract LinkedListAristas adyacentesA(int origen);    
}

class GrafoListaAdyImp extends Grafo{
    int v; // cantidad de vertices
    int a; // cantidad de aristas
    boolean esDirigido; // indica si el grafo es dirigido
    boolean esPonderado; // indica si el grafo es ponderado

    // Crea un grafo con V vertices (del 1 al V)
    // O(V)   
    GrafoListaAdyImp(int cantidadDeVertices, boolean esDir, boolean esPond){
        //constructor padre
        super(cantidadDeVertices);        
        
        v = cantidadDeVertices;
        a = 0;
        esDirigido = esDir;
        esPonderado = esPond;
        
        // listaAdy = new ListaAristas[V + 1];
        // for (int i = 1; i <= V; i++)
        // {
        //     listaAdy[i] = NULL; // inicializa todas las listas como vacias
        // } en teoria esto no tengo porque hacerlo
    }
    // O(1)
    void aniadirArista(int v, int w, int p){
        int pesoArista = esPonderado ? p : 1; // en el caso de ser ponderado se toma en cuenta el parametro
        Arista a1 = new Arista(v, w, pesoArista);
        listaAdy[v].push(a1); // se agrega al ppio de la lista de los adyacentes al veritce v 
        a++; //sumo arista
        if (!esDirigido)// en caso de no ser dirigido podemos duplicar la arista hacia el otro sentido w->v
        {
            Arista a2 = new Arista (w, v, pesoArista);
            listaAdy[w].push(a2);
        } 
    }    
    // si bien esta funcion podria ser O(1) si retornamos la lista original, es peligroso si la manipulan => corrompiendo el grafo
    // O(V) pc En Java O(1)    
    LinkedListAristas adyacentesA(int origen){
        return listaAdy[origen];
    }
    //O(v)
    int[] initGradoDeEntrada(GrafoListaAdyImp g, int v){

        int [] gradoEntradas = new int[v + 1];

        for (int i = 1; i <= v; i++)
        {
            LinkedListAristas adyacentes = g.adyacentesA(i);
            NodoLista n = adyacentes.head;
            while (n != null)
            {
                gradoEntradas[n.dato.destino]++;
                n  = n.sig;
            }
        }
        return gradoEntradas;
    }

    void ordenacionTopologica(GrafoListaAdyImp g, int v){
        //O(A)
        int[] gradoEntrada = initGradoDeEntrada(g, v);
        //O(V)
        Queue cola = new Queue(v);        
        //O(V)
        for(int i=1 ; i<= Math.abs(v); i++ ){
            if(gradoEntrada[i]==0){
                //Lo marco para procesar
                cola.enqueue(v);
            }
        }

        int vertice,cont = 0;
        while(!cola.isEmpty(cola)){
            //obtengo Vertice grando entrante cero no visitado
            vertice = cola.dequeue();
            cont++;
            //creo copia de lista y recorro
            LinkedListAristas copyListaAdy = adyacentesA(vertice);
            while (copyListaAdy.head != null){
                gradoEntrada[copyListaAdy.head.dato.destino]--;
                if(gradoEntrada[copyListaAdy.head.dato.destino]==0){
                    cola.enqueue(copyListaAdy.head.dato.destino);
                }
                copyListaAdy.head = copyListaAdy.head.sig;
            }            
        }

        if(cont < v){
            System.out.print(1);
        }
    }

}

public class Ejercicio4 {
    
}
