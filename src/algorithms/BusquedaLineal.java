package algorithms;

public class BusquedaLineal {
    
    // VERSIÓN ITERATIVA [O(n)]
    public static int linealIterativa(int[] arreglo, int objetivo) {
        for (int i = 0; i < arreglo.length; i++) {
            if (arreglo[i] == objetivo) return i;
        }
        return -1;
    }

    // VERSIÓN RECURSIVA [O(n)]
    public static int linealRecursiva(int[] arreglo, int objetivo, int indice) {
        // Caso base: no se encontró el elemento
        if (indice >= arreglo.length) return -1;
        
        // Caso base: elemento encontrado
        if (arreglo[indice] == objetivo) return indice;
        
        // Llamada recursiva avanzando al siguiente índice
        return linealRecursiva(arreglo, objetivo, indice + 1);
    }
}