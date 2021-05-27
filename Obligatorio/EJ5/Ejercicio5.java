//package EJ5;
import java.util.Scanner;

abstract class Grafo{
    // pre:
    //  - v y w son vertices validos del grafo
    //  - no existe la arista v w
    // pos: agrega la arista v w con su respectivo peso al grafo
    abstract void aniadirArista(int v, int w , int p);
}

class GrafoMatrizAdImp extends Grafo{
    int INF = Integer.MAX_VALUE;
    int v; // cantidad de vertices
    boolean esDirigido; // indica si el grafo es dirigido
    boolean esPonderado; // indica si el grafo es ponderado
    int[][] grafo;

    // Crea un grafo con V vertices (del 1 al V)
    // O(V^2)   
    GrafoMatrizAdImp(int cantidadDeVertices, boolean esDir, boolean esPond){
        v = cantidadDeVertices;
        esDirigido = esDir;
        esPonderado = esPond;
        //inicializamos matriz
        grafo = new int[v+1][v+1];
        for(int i=1; i<=v; i++) {
            for(int j=1; j<=v; j++) {
                grafo[i][j] = INF;
            }
        }

    }
    // O(1)
    void aniadirArista(int v, int w, int p){
        if(esPonderado){
            grafo[v][w] = p;
        }                 
    }    

    private int[][] generarMatrizAdy() {
        int[][] ret = new int[v+1][v+1];
        for(int i=1; i<=v; i++) {
            for(int j=1; j<=v; j++) {
                // retiro las aristas a uno mismo
                ret[i][j] = i==j ? INF : grafo[i][j];
            }
        }
        return ret;
    }

    private int[][] initMatrizVengo() {
        int[][] ret = new int[v+1][v+1];
        for(int i=1; i<=v; i++) {
            for(int j=1; j<=v; j++) {
                ret[i][j] = grafo[i][j] != INF ? i : -1;
            }
        }
        return ret;
    }

    public void floyd() {
        int[][] matriz = generarMatrizAdy();
        int[][] vengo =  initMatrizVengo();

        for(int k=1; k<=v; k++) {
            for(int i=1; i<=v; i++) {
                for(int j=1; j<=v; j++) {
                    if(matriz[i][k]!=INF && matriz[k][j]!= INF && matriz[i][j] > matriz[i][k] + matriz[k][j]) {
                        matriz[i][j] = matriz[i][k] + matriz[k][j];
                        vengo[i][j] = k;
                    }
                }
            }
        }
        for(int i=1; i<=v; i++) {
            //System.out.println("Desde origen " + i + "\n");
            for(int j=1; j<=v; j++) {
                if(i != j) {
                    //Entonces Hay Camino
                    System.out.println(matriz[i][j]);                    
                }else{
                    System.out.println(-1); 
                }
            }            
        }
    }

}

public class Ejercicio5 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        //obtengo vertices
        int v = sc.nextInt();
        //System.out.println("Cant Vertices: " + v);
        //obtengo aristas
        int e = sc.nextInt();        
        //System.out.println("Cant Aristas: "+ e);
        //Creamos el Grafo
        GrafoMatrizAdImp g = new GrafoMatrizAdImp(v, true, true); 
        //skip one line
        sc.nextLine();
        int vi, wi, ci = 0;
        //O(A)
        for (int i = 1; i <= e; i++) {
            vi = sc.nextInt();
            wi = sc.nextInt();
            ci = sc.nextInt();
            //O(1)
            g.aniadirArista(vi, wi, ci);
            //System.out.println("Linea -->" + vi +" "+ wi +" " + ci);            
            //sc.nextLine();
        }
        sc.close();
        //O(V^3)
        g.floyd();                          
    }//O(T) = O(A) + O(V^3) = O(V^3)
}
