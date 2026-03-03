package algorithms;

/**
 * ============================================================
 * A3 - FACTORIAL
 * ============================================================
 * Calcula el producto de todos los números desde 1 hasta n.
 * * Complejidad temporal (Ambas versiones):
 * - O(n) — Se realizan exactamente n multiplicaciones.
 *
 * Complejidad espacial:
 * - Iterativo: O(1) — Solo usa una variable acumuladora.
 * - Recursivo: O(n) — Debido a la pila de llamadas (call stack).
 * ============================================================
 */
public class Factorial {

    // VERSIÓN ITERATIVA [O(n)]
    public static double iterativo(int n) {
        if (n < 0) throw new IllegalArgumentException("n no puede ser negativo");
        double resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    // VERSIÓN RECURSIVA [O(n)]
    public static double recursivo(int n) {
        if (n < 0) throw new IllegalArgumentException("n no puede ser negativo");
        // Caso base
        if (n <= 1) return 1;
        // Llamada recursiva
        return n * recursivo(n - 1);
    }
}