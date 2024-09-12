/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */

public class ejer2{

    public static void main(String[] args) {
        // Coeficientes del sistema de ecuaciones
        double[][] coeficientes = {
                {52, 20, 25},  // Arena
                {30, 50, 20},  // Grano fino
                {18, 30, 55}   // Grano grueso
        };

        // Requerimientos de arena, grano fino, y grano grueso
        double[] requerimientos = {4800, 5810, 5690};

        // Resolver el sistema de ecuaciones
        double[] resultado = resolverSistema(coeficientes, requerimientos);

        if (resultado != null) {
            System.out.println("Cantidad de metros cúbicos de material a transportar:");
            System.out.printf("De la Cantera 1: %.2f m3%n", resultado[0]);
            System.out.printf("De la Cantera 2: %.2f m3%n", resultado[1]);
            System.out.printf("De la Cantera 3: %.2f m3%n", resultado[2]);
        } else {
            System.out.println("El sistema no tiene solución o tiene infinitas soluciones.");
        }
    }

    // Método para resolver el sistema de ecuaciones utilizando eliminación gaussiana
    public static double[] resolverSistema(double[][] matriz, double[] resultados) {
        int n = resultados.length;

        for (int i = 0; i < n; i++) {
            // Hacer el pivote de la fila i
            int max = i;
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(matriz[j][i]) > Math.abs(matriz[max][i])) {
                    max = j;
                }
            }

            // Intercambiar filas en la matriz
            double[] temp = matriz[i];
            matriz[i] = matriz[max];
            matriz[max] = temp;

            // Intercambiar resultados correspondientes
            double t = resultados[i];
            resultados[i] = resultados[max];
            resultados[max] = t;

            // Si la matriz es singular, no tiene solución
            if (Math.abs(matriz[i][i]) <= 1e-10) {
                return null;
            }

            // Hacer las filas por debajo de la fila pivote iguales a 0 en la columna i
            for (int j = i + 1; j < n; j++) {
                double factor = matriz[j][i] / matriz[i][i];
                resultados[j] -= factor * resultados[i];
                for (int k = i; k < n; k++) {
                    matriz[j][k] -= factor * matriz[i][k];
                }
            }
        }

        // Resolver el sistema triangular superior
        double[] solucion = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double suma = 0.0;
            for (int j = i + 1; j < n; j++) {
                suma += matriz[i][j] * solucion[j];
            }
            solucion[i] = (resultados[i] - suma) / matriz[i][i];
        }

        return solucion;
    }
}
