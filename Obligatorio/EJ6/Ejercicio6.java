//package EJ6;
import java.util.Scanner;

class Arista{
    int origen;
    int destino;
    int peso;
    Arista(int o, int d, int p){ origen = o ; destino = d; peso = p;}
    Arista(int o, int d){ origen = o ; destino = d; peso = 0;}
}

class NodoLista {
        Arista dato;
        NodoLista sig;
        NodoLista(Arista a){ dato = a; sig = null;}
        NodoLista(Arista a, NodoLista sigNodo){dato = a; sig = sigNodo;}
}

class LinkedListAristas {
    NodoLista head; //head of list
    int cantElementos=0;
    //agregar nodo al principio de la lista
    public void push (Arista new_Arista){
        //creamos el nuevo nodo.
        NodoLista newNode = new NodoLista(new_Arista);
        //Sig del nuevo nodo, apunta al valor del head.
        newNode.sig=head;
        //head apunta al nuevo nodo.
        head = newNode;
        cantElementos++;
    }

    public int getCantElementos(){
        return cantElementos;
    }
}

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
        // System.out.println(item
        //                    + " enqueued to queue");
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
        //inicializo el array que contiene la implementacion de lista
        listaAdy = new LinkedListAristas[cantidadDeVertices+1];
        //Inicializo cada elemento del array como una nueva Lista
        for(int i=1 ; i<=cantidadDeVertices; i++){
            listaAdy[i] = new LinkedListAristas();
        }
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
    //O(V)
    int[] initGradoDeEntrada(GrafoListaAdyImp g, int v){
        int [] gradoEntradas = new int[v + 1];
        for (int i = 1; i <= v; i++){
            //System.out.println("Grado Entrada V:" + i);
            LinkedListAristas adyacentes = g.adyacentesA(i);
            NodoLista n = adyacentes.head;
            while (n != null){
                //System.out.println("Vertice Adyacente:" + n.dato.destino);
                gradoEntradas[n.dato.destino]++;
                n  = n.sig;
            }
        }
        for (int i = 1; i <= v; i++){
            System.out.println("Grado Entrada V:" + i + "es: " + gradoEntradas[i]);            
        }
        return gradoEntradas;
    }
    //O(V)
    int[] initColorVertice(int v){
        int[] colorVertices = new int[v+1];
        for(int i=1 ; i<=v; i++){
            colorVertices[i]=2;
        }
        return colorVertices;
    }
    //O(V + A)
    Boolean grafoBipartita(GrafoListaAdyImp g, int v){
        Boolean bipartito = true;
        int[] gradoEntrada = initGradoDeEntrada(g, v); 
        int[] colorVertices = initColorVertice(v);
        //O(V)
        Queue cola = new Queue(v);        
        //O(V)
        for(int i=1 ; i<= Math.abs(v); i++ ){
            if(gradoEntrada[i]>0){
                //Lo marco para procesar
                cola.enqueue(i);
            }
        }
        int vertice = 0;
        while(!cola.isEmpty(cola)){
            //obtengo Vertice grando entrante cero no visitado
            vertice = cola.dequeue();
            System.out.println("Desencolamos el: " + vertice);
            //Si no está pintado, lo pinto
            if(colorVertices[vertice]==2){
                System.out.println("Pinto el vertice: " + vertice + " con: " + 0);
                colorVertices[vertice]=0;
            }else{
                System.out.println("Estaba pintado el vertice: " + vertice + " con: " + 0);
            }            
            //creo copia de lista y recorro
            LinkedListAristas copyListaAdy = adyacentesA(vertice);
            while (copyListaAdy.head != null){
                System.out.println("Hay elementos en la lista de adyacencia del:" + vertice);
                gradoEntrada[copyListaAdy.head.dato.destino]--;
                //si mi adyacente no esta pintado, lo pinto , diferente a mi.                                
                if(colorVertices[copyListaAdy.head.dato.destino]==2){
                    colorVertices[copyListaAdy.head.dato.destino]=1;
                    System.out.println("Adyacente no pintado, lo pinto con el otro color");
                }else if(colorVertices[copyListaAdy.head.dato.destino]!=2 && colorVertices[vertice]==colorVertices[copyListaAdy.head.dato.destino]){
                    //Ya esta pintado. Tiene mi mismo color.
                    System.out.println("Adyacente pintado y con el mismo color a mi");                    
                    return bipartito = false;
                }else if(colorVertices[copyListaAdy.head.dato.destino]!=2 && colorVertices[vertice]!=colorVertices[copyListaAdy.head.dato.destino]){
                    System.out.println("Adyacente pintado y distinto a mi");
                }
                //Me fijo si ya quedo para procesar y en caso afirmativo lo encolo
                if(gradoEntrada[copyListaAdy.head.dato.destino]==0){
                    cola.enqueue(copyListaAdy.head.dato.destino);
                }
                copyListaAdy.head = copyListaAdy.head.sig;
            }            
        }
        return bipartito;        
    }

}

public class Ejercicio6 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        //obtengo vertices
        int v = sc.nextInt();
        //System.out.println("Cant Vertices: " + v);
        //obtengo aristas
        int e = sc.nextInt();        
        //System.out.println("Cant Aristas: "+ e);
        //Creamos el Grafo
        GrafoListaAdyImp g = new GrafoListaAdyImp(v, false, false); 
        //skip one line
        sc.nextLine();
        int vi, wi, ci = 0;
        //O(A)
        for (int i = 1; i <= e; i++) {
            vi = sc.nextInt();
            wi = sc.nextInt();
            //O(1)
            g.aniadirArista(vi, wi, wi);
            //ci = sc.nextInt()!=0 ? sc.nextInt() : 1;
            //System.out.println("Linea -->" + vi +" "+ wi +" ");            
            sc.nextLine();
        }
        sc.close();
        //O(V + A)        
        if(g.grafoBipartita(g, v)){
            System.out.println(1);
        }else{
            System.out.println(0);
        }
    }//O(T) = O(A) + O(V+A) = O(V+A) 
}
