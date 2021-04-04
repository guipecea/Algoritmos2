import java.util.Scanner;

public class Ejemplo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] vec = new int[n];
        for (int i = 0; i < n; i++) {
            vec[i] = sc.nextInt();
        }

        sort(vec, n);

        for (int i = 0; i < n; i++) {
            System.out.println(vec[i]);
        }
    }

    // metodo burbuja
    private static void sort(int[] v, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (v[j] < v[i]) {
                    int aux = v[i];
                    v[i] = v[j];
                    v[j] = aux;
                }
            }
        }
    }
}
