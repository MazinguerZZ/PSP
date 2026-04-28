package Tarea1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String os = System.getProperty("os.name");
        boolean linux = os.toLowerCase().contains("linux");

        while (true) {
            System.out.println("\nSistema: " + os);
            System.out.println("Elige opción:\n1. Ejecutar un ping solicitando el destino y mostrar el resultado por pantalla." +
                    "\n2. Realizar una lista de los archivos y ficheros a un archivo indicado por el usuario.\n" +
                    "3. Leer los procesos del sistema y permitir cerrar el indicado por el usuario " +
                    "mediante el PID.\n" +
                    "4. Ejecutar un navegador con la URL indicada por el usuario.\n" +
                    "5.- Salir");
            String op = scanner.nextLine();
            try {
                switch (op) {
                    case "1":
                        System.out.print("IP o dominio: ");
                        String ip = scanner.nextLine();
                        ejecutarArray(linux
                                ? new String[]{"ping", "-c", "4", ip}
                                : new String[]{"ping", ip});
                        break;

                    case "2":
                        System.out.print("Ruta a listar: ");
                        String ruta = scanner.nextLine();
                        ejecutarArray(linux
                                ? new String[]{"ls", "-la", ruta}
                                : new String[]{"cmd", "/c", "dir", ruta});
                        break;

                    case "3":
                        ejecutarArray(linux
                                ? new String[]{"ps", "aux"}
                                : new String[]{"tasklist"});
                        System.out.print("PID a terminar (0 para cancelar): ");
                        String pid = scanner.nextLine();
                        if (!pid.equals("0")) {
                            ejecutarArray(linux
                                    ? new String[]{"kill", "-9", pid}
                                    : new String[]{"taskkill", "/PID", pid, "/F"});
                            System.out.println("Proceso terminado con éxito");
                        }
                        break;

                    case "4":
                        System.out.print("URL: ");
                        String url = scanner.nextLine();
                        ejecutarArray(linux
                                ? new String[]{"google-chrome", url}
                                : new String[]{"cmd", "/c", "start", url});
                        System.out.println("Navegador abierto con éxito");
                        break;

                    case "5":
                        System.out.println("Saliendo...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Opción no válida, elige entre 1 y 5.");
                }
            } catch (IOException | InterruptedException e) {
                System.out.println("Error al ejecutar el comando: " + e.getMessage());
            }
        }
    }

    static void ejecutarArray(String[] cmd) throws Exception {
        Process p = Runtime.getRuntime().exec(cmd);

        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) System.out.println(line);

        p.waitFor();
    }
}