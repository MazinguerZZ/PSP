package tarea2;

import java.io.*;
import java.util.*;

public class Ejer2 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("> ");

        while (true) {
            String texto = sc.nextLine();

            if (texto.equals("fin")) {
                break;
            }

            for (int i = 0; i < texto.length(); i++) {
                try {
                    ProcessBuilder pb = new ProcessBuilder("java", "-cp", "out/production/Tarea 2", "tarea2.NumerosAleatorios");

                    Process process = pb.start();

                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(process.getInputStream()));

                    BufferedReader errorReader = new BufferedReader(
                            new InputStreamReader(process.getErrorStream()));

                    String linea;
                    boolean tieneSalida = false;

                    while ((linea = reader.readLine()) != null) {
                        System.out.print(linea);
                        tieneSalida = true;
                    }
                    reader.close();

                    String errorLinea;
                    while ((errorLinea = errorReader.readLine()) != null) {
                        System.err.println("ERROR: " + errorLinea);
                    }
                    errorReader.close();

                    int exitCode = process.waitFor();

                    if (!tieneSalida) {
                        System.out.print("0");
                    }

                } catch (Exception e) {
                    System.err.println("Error general: " + e.getMessage());
                }
            }

            System.out.println();
            System.out.print("> ");
        }
        sc.close();
    }
}