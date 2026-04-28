import java.util.InputMismatchException;
import java.util.Scanner;

public class Banco {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int numClientes = 0;

        // Control de errores en la entrada
        boolean entradaValida = false;
        while (!entradaValida) {
            System.out.print("Número de clientes: ");
            try {
                numClientes = entrada.nextInt();
                if (numClientes <= 0) {
                    System.out.println("Error: debe ser mayor que 0.");
                } else {
                    entradaValida = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: introduce un número entero válido.");
                entrada.nextLine();
            }
        }
        entrada.close();

        CuentaBancaria cuenta = new CuentaBancaria();
        System.out.println("\nSaldo inicial: " + cuenta.getSaldo() + "€");
        System.out.println("--------------------------------\n");

        // Crear e iniciar los hilos
        Cliente[] clientes = new Cliente[numClientes];
        for (int i = 0; i < numClientes; i++) {
            clientes[i] = new Cliente(cuenta, "Cliente-" + (i + 1));
            clientes[i].start();
        }

        // Esperar a que todos terminen
        for (int i = 0; i < numClientes; i++) {
            try {
                clientes[i].join();
            } catch (InterruptedException e) {
                System.out.println("Error esperando al cliente.");
            }
        }

        System.out.println("\n--------------------------------");
        System.out.println("Saldo final: " + cuenta.getSaldo() + "€");
    }
}