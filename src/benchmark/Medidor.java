package benchmark;

/**
 * ============================================================
 * MEDIDOR DE TIEMPO
 * ============================================================
 * Mide cuántos milisegundos tarda en ejecutarse un algoritmo.
 * Ejecuta el algoritmo REPETICIONES veces y calcula el promedio
 * para reducir el ruido de la JVM (JIT, GC, cache warming, etc.)
 *
 * USO PARA ESTUDIANTES:
 *   double tiempo = Medidor.medir(() -> Fibonacci.iterativo(n));
 * ============================================================
 */
public class Medidor {

    /** Cuántas veces se repite cada prueba para promediar */
    private static final int REPETICIONES = 10;

    // ----------------------------------------------------------------
    // INTERFAZ FUNCIONAL (permite pasar un método como parámetro)
    // ----------------------------------------------------------------
    /**
     * Representa cualquier bloque de código a medir.
     * Se usa con expresiones lambda: () -> miAlgoritmo(n)
     */
    @FunctionalInterface
    public interface Algoritmo {
        void ejecutar();
    }

    // ----------------------------------------------------------------
    // MÉTODO PRINCIPAL DE MEDICIÓN
    // ----------------------------------------------------------------
    /**
     * Mide el tiempo promedio de ejecución de un algoritmo.
     *
     * @param algoritmo bloque de código a medir (lambda)
     * @return tiempo promedio en milisegundos (ms)
     */
    public static double medir(Algoritmo algoritmo) {
        long totalNanos = 0;

        for (int i = 0; i < REPETICIONES; i++) {
            long inicio = System.nanoTime();   // ← reloj de alta resolución
            algoritmo.ejecutar();
            long fin    = System.nanoTime();
            totalNanos += (fin - inicio);
        }

        double promedioNanos = (double) totalNanos / REPETICIONES;
        return promedioNanos / 1_000_000.0;    // nanosegundos → milisegundos
    }

    // ----------------------------------------------------------------
    // UTILIDADES DE PRESENTACIÓN
    // ----------------------------------------------------------------
    /**
     * Imprime el encabezado de la tabla de resultados.
     */
    public static void imprimirEncabezado() {
        System.out.println("-".repeat(60));
        System.out.printf("%-14s | %-12s | %-8s | %s%n",
                "Algoritmo", "Versión", "n", "Tiempo (ms)");
        System.out.println("-".repeat(60));
    }

    /**
     * Imprime una fila de resultado con formato alineado.
     *
     * @param algoritmo nombre del algoritmo
     * @param version   "Iterativo" o "Recursivo"
     * @param n         tamaño de entrada
     * @param tiempoMs  tiempo medido en ms
     */
    public static void imprimirFila(String algoritmo, String version, int n, double tiempoMs) {
        System.out.printf("%-14s | %-12s | n=%-5d | %.6f ms%n",
                algoritmo, version, n, tiempoMs);
    }
}
