//Implementacion de Lista -> Lista Simplemente Encadenada.

class LinkedList {
    Node head; //head of list

    //se define static para que main() pueda acceder a ella.
    static class Node {
        int data;
        Node next;
        Node (int d){ data = d; next = null; } //Constructor.
    }

    //imprimir lista 
    public void printLinkedList(){
        //apuntamos n al head de la lista.
        Node n = head;

        while (n != null){
          //recorremos la lista.  
            System.out.print( n.data + " ");
            n = n.next; //apuntamos al siguiente nodo de la lista.
        }

    }

    //agregar nodo al principio de la lista
    public void push (int new_data){
        //creamos el nuevo nodo.
        Node newNode = new Node(new_data);

        //Sig del nuevo nodo, apunta al valor del head.
        newNode.next = head;

        //head apunta al nuevo nodo.
        head = newNode;
    }

    //agregar nodo despues de uno dado
    public void insertAfter(int new_data, Node prev_node){

        //check del nodo previo si es null.
        if (prev_node == null){
            System.out.println("El nodo indicado no puede ser null");
            return;
        }
        
        Node new_node = new Node(new_data);

        new_node.next = prev_node.next;

        prev_node.next = new_node;

    }

    //agregar nodo al final 
    public void append(int new_data){

        Node newNode = new Node(new_data);

        if (head == null){

            head = newNode;
            return;
        }

        newNode.next = null ;

        Node last = head;

        while (last.next != null){
            last = last.next;            
        }

        last.next = newNode;

        return;
    }

    public static void main (String[] args){
        //inicializamos lista vacia.
        LinkedList linkedList = new LinkedList();

        //Creamos 3 nodos, dinamicamente y guardamos las referencias.
        linkedList.head = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);

        //asignamos primer link de la lista
        linkedList.head.next = second;

        //asignamos segundo link de la lista
        second.next = third;

        linkedList.push(4);

        linkedList.push(5);
        
        linkedList.append(22);

        linkedList.insertAfter(88,linkedList.head.next);

        //imprimimos la lista
        System.out.println("Created Linked list is: ");

        linkedList.printLinkedList();
    }

}