/*
Ejercicio 1:

    Utilizando un AVL como estructura auxiliar, debe ordenar una secuencia de numeros.

    Formato de entrada:
        N -> nro de casos
        num1
        num2
        ...
        numN

    Nota: puede haber numeros repetidos (debe considerarlo). https://www.geeksforgeeks.org/avl-with-duplicate-keys
*/    
import java.util.Scanner;

class Ejercicio_1 {

    static class Node {
        int key, height, count;
        Node left, right;

        Node (int d) { key=d; height= 1; count=1;} //constructor de nodo
    }

    static class AVLTree {

        Node root;

        //obtener altura del nodo, arbol.
        int height(Node n){
            
            if (n == null){
                return 0;
            }
            return n.height;
        }

        //obtener mayor de 2 enteros.
        int max (int a, int b ){
            return (a > b) ? a : b;
        }

        Node rightRotate(Node y) {
                Node x = y.left;
                Node T2 = x.right;
        
                // Perform rotation
                x.right = y;
                y.left = T2;
        
                // Update heights
                y.height = max(height(y.left), height(y.right)) + 1;
                x.height = max(height(x.left), height(x.right)) + 1;
        
                // Return new root
                return x;
        }

        Node leftRotate(Node x) {
            Node y = x.right;
            Node T2 = y.left;
    
            // Perform rotation
            y.left = x;
            x.right = T2;
    
            //  Update heights
            x.height = max(height(x.left), height(x.right)) + 1;
            y.height = max(height(y.left), height(y.right)) + 1;
    
            // Return new root
            return y;
        }

        //Obtener balance de un arbol
        int getBalance(Node N) {

            if (N == null)
                return 0;
    
            return height(N.left) - height(N.right);
        }

        Node insert(Node node, int key) {
        
            /* 1.  Perform the normal BST insertion */
            if (node == null)
                return (new Node(key));

            if (key < node.key)
                node.left = insert(node.left, key);
            else if (key > node.key)
                node.right = insert(node.right, key);
            else {
                //Duplicate key
                (node.count)++;
                return node;
            } 
                
            /* 2. Update height of this ancestor node */
            node.height = 1 + max(height(node.left),height(node.right));

            /* 3. Get the balance factor of this ancestor
                node to check whether this node became
                unbalanced */
            int balance = getBalance(node);

            // If this node becomes unbalanced, then there
            // are 4 cases Left Left Case
            if (balance > 1 && key < node.left.key)
                return rightRotate(node);

            // Right Right Case
            if (balance < -1 && key > node.right.key)
                return leftRotate(node);

            // Left Right Case
            if (balance > 1 && key > node.left.key) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
            // Right Left Case
            if (balance < -1 && key < node.right.key) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            /* return the (unchanged) node pointer */
            return node;
        }

        // A utility function to print preorder traversal
        // of the tree.
        // The function also prints height of every node
        void preOrder(Node node) {
            if (node != null) {
                System.out.print(node.key + "\n");
                preOrder(node.left);
                preOrder(node.right);
            }
        }

    }

    public static void main (String[] args){

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        //int[] vec = new int[n];
        AVLTree tree = new AVLTree();

        for (int i = 0; i < n; i++) {
            // vec[i] = sc.nextInt();
            tree.root = tree.insert(tree.root,sc.nextInt());
        }

        //sort(vec, n);
        tree.preOrder(tree.root);
    }
}