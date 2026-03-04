package algorithms;

public class OrdenamientoBurbuja {

    // VERSIÓN ITERATIVA [O(n^2)]
    public static void burbujaIterativa(int[] arreglo) {
        int n = arreglo.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arreglo[j] > arreglo[j + 1]) {
                    int temp = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = temp;
                }
            }
        }
    }

    // VERSIÓN RECURSIVA [O(n^2)]
    public static void burbujaRecursiva(int[] arreglo, int n) {
        // Caso base: si el tamaño es 1, ya está ordenado
        if (n == 1) return;

        // Una pasada del burbuja (mueve el mayor al final)
        for (int i = 0; i < n - 1; i++) {
            if (arreglo[i] > arreglo[i + 1]) {
                int temp = arreglo[i];
                arreglo[i] = arreglo[i + 1];
                arreglo[i + 1] = temp;
            }
        }

        // Llamada recursiva para el resto del arreglo (n-1)
        burbujaRecursiva(arreglo, n - 1);
    }
}