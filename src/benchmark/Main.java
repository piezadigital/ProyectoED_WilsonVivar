package benchmark;

import algorithms.Fibonacci;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ============================================================
 * PUNTO DE ENTRADA — BENCHMARK FIBONACCI
 * ============================================================
 * Ejecuta ambas versiones de Fibonacci para distintos valores de n,
 * mide sus tiempos y exporta los resultados a un archivo CSV.
 *
 * CÓMO COMPILAR (desde la carpeta raíz del proyecto):
 *   javac -d out src/algorithms/Fibonacci.java src/benchmark/Medidor.java src/benchmark/Main.java
 *
 * CÓMO EJECUTAR:
 *   java -cp out benchmark.Main
 * ============================================================
 */
public class Main {

    // ----------------------------------------------------------------
    // TAMAÑOS DE PRUEBA
    // ----------------------------------------------------------------
    /**
     * Valores de n para los que se ejecutará el benchmark.
     *
     * ITERATIVO: puede manejar n grandes (hasta ~92 con long)
     * RECURSIVO: limitado a n ≤ 30 por la complejidad O(2^n)
     *   fib(30) → ~2.7 millones de llamadas
     *   fib(40) → ~2.7 mil millones de llamadas (tarda minutos)
     */
    private static final int[] TAMANOS = {5, 10, 15, 20, 25, 30};

    /** Ruta del archivo de resultados */
    private static final String CSV_PATH = "resultados/tiempos.csv";

    // ----------------------------------------------------------------
    // MAIN
    // ----------------------------------------------------------------
    public static void main(String[] args) {
        imprimirBanner();

        StringBuilder csv = new StringBuilder();
        csv.append("Algoritmo,Version,n,Resultado,Tiempo_ms\n");

        // ---- FIBONACCI ITERATIVO ----
        System.out.println("\n  FIBONACCI ITERATIVO  [O(n)]");
        Medidor.imprimirEncabezado();

        for (int n : TAMANOS) {
            final int fn = n;

            // Calcular resultado una vez (solo para mostrarlo)
            long resultado = Fibonacci.iterativo(fn);

            // Medir solo el algoritmo puro (sin I/O ni inicialización)
            double tiempoMs = Medidor.medir(() -> Fibonacci.iterativo(fn));

            Medidor.imprimirFila("Fibonacci", "Iterativo", n, tiempoMs);
            csv.append(String.format("Fibonacci,Iterativo,%d,%d,%.6f%n", n, resultado, tiempoMs));
        }

        // ---- FIBONACCI RECURSIVO ----
        System.out.println("\n  FIBONACCI RECURSIVO  [O(2^n)]");
        Medidor.imprimirEncabezado();

        for (int n : TAMANOS) {
            final int fn = n;

            long resultado = Fibonacci.recursivo(fn);
            double tiempoMs = Medidor.medir(() -> Fibonacci.recursivo(fn));

            Medidor.imprimirFila("Fibonacci", "Recursivo", n, tiempoMs);
            csv.append(String.format("Fibonacci,Recursivo,%d,%d,%.6f%n", n, resultado, tiempoMs));
        }

        // ---- ANÁLISIS DE DIFERENCIA ----
        System.out.println("\n  COMPARACIÓN ITERATIVO vs RECURSIVO");
        System.out.println("-".repeat(60));
        System.out.printf("%-8s | %-14s | %-14s | %s%n",
                "n", "Iterativo (ms)", "Recursivo (ms)", "Recursivo / Iterativo");
        System.out.println("-".repeat(60));

        for (int n : TAMANOS) {
            final int fn = n;
            double tIter = Medidor.medir(() -> Fibonacci.iterativo(fn));
            double tRec  = Medidor.medir(() -> Fibonacci.recursivo(fn));
            double factor = (tIter > 0) ? tRec / tIter : 0;

            System.out.printf("n=%-6d | %-14.6f | %-14.6f | %.1fx más lento%n",
                    n, tIter, tRec, factor);
        }

        // ---- EXPORTAR CSV ----
        exportarCSV(csv.toString());

        System.out.println("\n============================================================");
        System.out.println("  Resultados exportados → " + CSV_PATH);
        System.out.println("============================================================");
    }

    // ----------------------------------------------------------------
    // AUXILIARES
    // ----------------------------------------------------------------
    private static void imprimirBanner() {
        System.out.println("============================================================");
        System.out.println("  ESTRUCTURA DE DATOS — BENCHMARK FIBONACCI");
        System.out.println("  Universidad Da Vinci de Guatemala");
        System.out.println("  Ing. Brandon Chitay");
        System.out.println("============================================================");
    }

    private static void exportarCSV(String contenido) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_PATH))) {
            pw.print(contenido);
            System.out.println("\n  ✓ CSV generado exitosamente en: " + CSV_PATH);
        } catch (IOException e) {
            System.err.println("  ✗ Error al escribir CSV: " + e.getMessage());
        }
    }
}
