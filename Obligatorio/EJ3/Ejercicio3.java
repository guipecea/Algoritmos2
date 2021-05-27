//package EJ3;
import java.util.Scanner;
//Implementacion Utilizando un Hash Cerrado.
//Representa un nodo a hashear en la tabla de hash, con su clave y valor.
class HashNode{
        String key;
        int value;
        HashNode(String k){
            key = k;
            value = 1;
        }
        public String getKey(){
            return key;
        }
        public int getValue(){
            return value;
        }
        public void setValue(int i){
            value = i;
        }
}
//Representa la Tabla de Hash (un array), donde cada bucket es del tipo HashNodeLinkedList.
class ClosedHashTable_Words{
    //tamano tabla
    private int arrSize;
    //elementos en la tabla
    private int cantElements;
    //buckets que conforman la tabla
    private HashNode[] bucketArrayList; 

    private boolean esPrimo(int n){
        if (n <= 1)
            return false;
        if (n <= 3)
            return true;
        if (n % 2 == 0 || n % 3 == 0)
            return false;
        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        return true;
    }

    private int siguientePrimo(int N){
        // https://www.geeksforgeeks.org/program-to-find-the-next-prime-number/
        if (N <= 1)
            return 2;
        int prime = N;
        boolean found = false;
        while (!found)
        {
            prime++;
            if (esPrimo(prime))
                found = true;
        }
        return prime;
    }

    private int fnHash(String clave){
        // OPC 3
        // Ref: https://cseweb.ucsd.edu/~kube/cls/100/Lectures/lec16/lec16-15.html
        int h = 0;
        char character;
        int ascii;        
        for (int i = 0; i < clave.length(); i++){
            character = clave.charAt(i);
            ascii = (int)character;
            h = (31 * h + ascii);
        }
        return h;
    }

    public void insertar(String clave){
        //System.out.println("insertar clave: " + clave);
        int hashValue = fnHash(clave);
        int pos = hashValue % arrSize;
        int i = 1;
        boolean inserte = false;

        while (!inserte)
        {
            if (bucketArrayList[pos]==null){
                bucketArrayList[pos] = new HashNode(clave);
                cantElements++;
                inserte = true;
            }else if (bucketArrayList[pos].getKey().compareTo(clave) == 0){                                    
                bucketArrayList[pos].setValue(bucketArrayList[pos].getValue() + 1);
                inserte = true;                                
            }else{               
                // Lineal
                pos = (hashValue + i) % arrSize;
                // // Cuadratico
                // pos = (int)(hashValue + pow(i, 2)) % arrSize;

                // Doble Hash
                // int hashValue2 = this->fnHash2(clave);
                // pos = (int)(hashValue + i*hashValue2) % arrSize;
            }                        
            i++;
        }
    }

    public ClosedHashTable_Words(int n){
        arrSize = siguientePrimo(n);
        cantElements = 0;
        bucketArrayList = new HashNode[arrSize];
    }

    public void imprimir(){
        int cant = 0;
        //System.out.println("cant elements is: " + cantElements);
        for(int i=0; i < arrSize -1 ; i++){
            if(bucketArrayList[i]!=null && bucketArrayList[i].getValue()==2){
                //System.out.println("Clave: " + bucketArrayList[i].getKey() + " valor: " + bucketArrayList[i].getValue());
                cant++;
            }
            // else if (bucketArrayList[i]!=null && bucketArrayList[i].getValue()!=2){
            //     System.out.println("Clave: " + bucketArrayList[i].getKey() + " valor: " + bucketArrayList[i].getValue());                
            // }
        }
        System.out.println(cant);
    }
}

public class Ejercicio3{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        //obtengo cant
        int n = sc.nextInt();        
        ClosedHashTable_Words hashTable = new ClosedHashTable_Words(n);
        //skip one line
        sc.nextLine();

        //n elementos a insertar --> n
        //insertar --> O (1) CP | O (n) PC
        //O (n) CP  | O (n).n PC
        for (int i = 0; i < n; i++) {
            hashTable.insertar(sc.nextLine());
        }
        sc.close();
        //n elementos en la tabla de hash
        // O(n) CP || O(n) PC
        hashTable.imprimir();                
        //Orden total = Max (O(for), O(impr)) = n          
    }

}