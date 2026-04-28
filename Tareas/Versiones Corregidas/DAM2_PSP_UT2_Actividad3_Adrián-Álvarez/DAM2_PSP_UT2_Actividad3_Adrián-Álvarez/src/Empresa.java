import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Empresa {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int totalRecursos = 0;
        int cantidadColaboradores = 0;

        // Control de errores: número de recursos
        boolean entradaValida = false;
        while (!entradaValida) {
            System.out.print("Número de mesas/equipos disponibles: ");
            try {
                totalRecursos = entrada.nextInt();
                if (totalRecursos <= 0) {
                    System.out.println("Error: el número de recursos debe ser mayor que 0.");
                } else {
                    entradaValida = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: debe introducir un número entero válido.");
                entrada.nextLine(); // Limpiar el buffer
            }
        }

        // Control de errores: número de colaboradores
        entradaValida = false;
        while (!entradaValida) {
            System.out.print("Número de colaboradores (mayor que " + totalRecursos + "): ");
            try {
                cantidadColaboradores = entrada.nextInt();
                if (cantidadColaboradores <= totalRecursos) {
                    System.out.println("Error: el número de colaboradores debe ser mayor que " + totalRecursos + ".");
                } else {
                    entradaValida = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: debe introducir un número entero válido.");
                entrada.nextLine(); // Limpiar el buffer
            }
        }

        entrada.close();

        Semaphore mesasTrabajo = new Semaphore(totalRecursos);
        Semaphore equiposComputo = new Semaphore(totalRecursos);

        System.out.println("\n--- INICIANDO ACTIVIDAD DIARIA ---");
        System.out.println("Recursos: " + totalRecursos + " mesas y " + totalRecursos + " equipos");
        System.out.println("Colaboradores: " + cantidadColaboradores);
        System.out.println("----------------------------------\n");

        for (int indice = 1; indice <= cantidadColaboradores; indice++) {
            new Empleado(mesasTrabajo, equiposComputo, "Colaborador-" + indice).start();
        }
    }
}