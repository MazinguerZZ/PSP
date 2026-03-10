import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Empresa {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Número de mesas/equipos disponibles: ");
        int totalRecursos = entrada.nextInt();

        if (totalRecursos <= 0) {
            System.out.println("Error: el número de recursos debe ser mayor que 0");
            entrada.close();
            return;
        }

        System.out.print("Número de colaboradores (mayor que " + totalRecursos + "): ");
        int cantidadColaboradores = entrada.nextInt();

        if (cantidadColaboradores <= totalRecursos) {
            System.out.println("Error: el número de colaboradores debe ser mayor que " + totalRecursos);
            entrada.close();
            return;
        }

        Semaphore mesasTrabajo = new Semaphore(totalRecursos);
        Semaphore equiposComputo = new Semaphore(totalRecursos);

        System.out.println("\n--- INICIANDO ACTIVIDAD DIARIA ---");
        System.out.println("Recursos: " + totalRecursos + " mesas y " + totalRecursos + " equipos");
        System.out.println("Colaboradores: " + cantidadColaboradores);
        System.out.println("----------------------------------\n");

        for (int indice = 1; indice <= cantidadColaboradores; indice++) {
            new Empleado(mesasTrabajo, equiposComputo, "Colaborador-" + indice).start();
        }

        entrada.close();
    }
}