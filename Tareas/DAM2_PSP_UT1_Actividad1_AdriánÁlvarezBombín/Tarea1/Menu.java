package Tarea1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        String os = System.getProperty("os.name");
        boolean linux = os.toLowerCase().contains("linux");


        while (true) {
            System.out.println("\nSistema: " + os);
            System.out.println("Elige opción:\n1. Ejecutar un ping solicitando el destino y mostrar el resultado por pantalla." +
                    "\n2. Realizar una lista de los archivos y ficheros a un archivo indicado por el usuario.\n" +
                    "3. Leer los procesos del sistema y permitir cerrar el indicado por el usuario" +
                    "mediante el PID.\n" +
                    "4. Ejecutar un navegador con la URL indicada por el usuario.\n" +
                    "5.- Salir");
            String op = scanner.nextLine();
            try {
                switch (op) {
                    case "1":
                        System.out.println("IP: ");
                        ejecutar(linux ? "ping -c 4 " + scanner.nextLine() : "ping " + scanner.nextLine());
                        break;

                    case "2":
                        System.out.println("Lista de archivos y ficheros: ");
                        ejecutar(linux ? "ls -la " + scanner.nextLine() : "dir " + scanner.nextLine());
                        break;

                    case "3":
                        ejecutar(linux ? "ps aux" : "tasklist");
                        System.out.println("Pid: ");
                        String p = scanner.nextLine();
                        System.out.println("Proceso terminado con exito");
                        if (!p.equals("0")) ejecutar(linux ? "kill -9 " + p : "taskkill /PID " + p + " /F");
                        break;

                    case "4":
                        System.out.print("URL: ");
                        Runtime.getRuntime().exec(linux ? "google-chrome\n " + scanner.nextLine() : "cmd /c start " + scanner.nextLine());
                        System.out.println("Navegador abierto con exito");
                        break;
                    case "5":
                        return;
                }
            }catch (IOException  | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    static void ejecutar (String cmd) throws Exception {
        Process p = Runtime.getRuntime().exec(cmd);
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) System.out.println(line);
        p.waitFor();
    }
}