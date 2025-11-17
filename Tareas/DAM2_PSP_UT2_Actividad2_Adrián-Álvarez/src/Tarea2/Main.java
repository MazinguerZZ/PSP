package Tarea2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numClientes = 0;
        while (numClientes <= 0) {
            try {
                System.out.print("Introduce el número de clientes: ");
                numClientes = sc.nextInt();
                if (numClientes <= 0) {
                    System.out.println("Error: Debe ser un número positivo.");
                }
            } catch (Exception e) {
                System.out.println("Error: Introduce un número válido.");
                sc.nextLine();
            }
        }

        // Crear cuenta compartida
        CuentaBancaria cuenta = new CuentaBancaria();
        List<Thread> hilosClientes = new ArrayList<>();

        // Crear e iniciar hilos
        for (int i = 0; i < numClientes; i++) {
            Cliente cliente = new Cliente(cuenta, "CLIENTE" + (i + 1));
            Thread hilo = new Thread(cliente);
            hilosClientes.add(hilo);
            hilo.start();
        }

        // Esperar a que terminen todos
        for (Thread hilo : hilosClientes) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.out.println("Error esperando hilos");
            }
        }

        // RESULTADO FINAL
        System.out.printf("Saldo inicial: %.2f€\n", CuentaBancaria.SALDO_INICIAL);
        System.out.printf("Saldo final: %.2f€\n", cuenta.getSaldoActual());

        // Calcular total de transacciones
        float totalTransacciones = cuenta.getTotalDepositos() + cuenta.getTotalRetiros();
        System.out.printf("Total transacciones: %.2f€\n", totalTransacciones);

        // Calcular descuadre
        float saldoEsperado = CuentaBancaria.SALDO_INICIAL + cuenta.getTotalDepositos() - cuenta.getTotalRetiros();
        float descuadre = cuenta.getSaldoActual() - saldoEsperado;
        System.out.printf("Descuadre: %.2f€\n", descuadre);

        sc.close();
    }
}