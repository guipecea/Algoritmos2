import java.util.Scanner;

public class Ejercicio2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arrayHeap = new int[n+1];
        boolean flag = true;
        int result = 1;
        //El primero carga 0 para simular el array del MinHeap
        arrayHeap[0] = 0;
        int i = 2;

        //Inserto Primer Elemento.    
        arrayHeap[1] = sc.nextInt();
        //obtengo siguiente.
        int valorLinea = sc.nextInt();
        //n-1 elementos a insertar --> n-1        
        while( i < (arrayHeap.length - 1) && flag){
            //Obtengo mi padre y me fijo si soy mayor que el
            //O 1 siempre.            
            //System.out.println("MiPadre: " + arrayHeap[i/2] + " Hijo: " + sigValor); 
            if ( valorLinea < arrayHeap[i/2] ){
                flag = false;
                result = 0;                
            }
            else{
                arrayHeap[i] = valorLinea;
                valorLinea = sc.nextInt();                  
            }          
            i++;
        }// O n-1 + 1 = n  PC        
        System.out.println(result);            
    }

}