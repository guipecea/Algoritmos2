package algoritmos2;

/**
 *
 * @author crodrigurez
 */
public class LinkedList{
    public static void main(String[] args)
    {
        
        Grafo grafo = new Grafo(5);
        grafo.agregarArista(1,3);
        grafo.agregarArista(1,2);
        grafo.agregarArista(1,5);

        grafo.agregarArista(4,5);
        grafo.agregarArista(4,3);
        grafo.agregarArista(3,2);

        grafo.imprimir();
       
    }

}
class Nodo {
   
        public Arista arista;
        public Nodo sig;
   
        // Constructor
        Nodo(Arista d){
            arista = d;
            sig = null;
        }
        
        Nodo(Arista d, Nodo sig){
            this.arista = d;
            this.sig = sig;
        }
    }
 
class Arista{
    public int origen;
    public int destino;
    
    public Arista(int origen, int destino){
        this.origen=origen;
        this.destino=destino;
    }
}   


class Grafo{
    private Nodo[] listaAdy;
    private int V;
    private int E;
    
    //O(V)
    public Grafo(int V, int E){
        this.V=V;
        this.E=E;
        this.listaAdy=new Nodo[V+1];
        for(int i=1; i<=V; i++){
            listaAdy[i]=null;
        }
    }
    
        //O(V)
    public Grafo(int V){
        this.V=V;
        this.E=0;
        this.listaAdy=new Nodo[V+1];
       for(int i=0; i<=V; i++){
            listaAdy[i]=null;
        }

    }
    
    public void agregarArista(int v, int w){
        
        Arista a1=new Arista(v, w);
        listaAdy[v] = new Nodo(a1, listaAdy[v]); 
        this.E++;
    }
// O(V) pc
    // si bien esta funcion podria ser O(1) si retornamos la lista original, es peligroso si la manipulan => corrompiendo el grafo
    Nodo adyacentesA(int origen)
    {
        // copio la lista
        Nodo listaRetorno = null;
        Nodo listaAuxiliar = listaAdy[origen];

        while (listaAuxiliar != null)
        {
            Arista aristaAuxiliar = listaAuxiliar.arista;
            listaRetorno=new Nodo(aristaAuxiliar, listaRetorno);
            listaAuxiliar = listaAuxiliar.sig;
        }

        return listaRetorno;
    }    
 
        // O(V + A)
    void imprimir()
    {
        // por cada vertice del 1 al V, imprimo sus adyacentes
        for (int i = 1; i <= V; i++){
            System.out.println("Adyacentes al vertice "+ i);
            Nodo adyacentes = this.adyacentesA(i);
            while (adyacentes != null){
                Arista arista = adyacentes.arista;
                System.out.print(i + "-");
                System.out.println("->" + arista.destino);
                adyacentes = adyacentes.sig;
            }
        }
    }
    
}   