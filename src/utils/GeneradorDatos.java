package utils;

import java.util.Random;

public class GeneradorDatos {
    /**
     * Genera un arreglo de enteros con números aleatorios.
     * @param n Tamaño del arreglo
     * @return Arreglo de enteros generado
     */
    public static int[] generarArreglo(int n) {
        int[] arr = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            // Números aleatorios entre 0 y n*2 para variar los datos
            arr[i] = random.nextInt(n * 2);
        }
        return arr;
    }
}