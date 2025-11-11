package Tarea2;

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

                    ProcessBuilder pb = new ProcessBuilder("java", "Tarea2.NumerosAleatorios");
                    pb.redirectErrorStream(true);

                    Process process = pb.start();


                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(process.getInputStream()));

                    String linea;
                    while ((linea = reader.readLine()) != null) {
                        System.out.print(linea);
                    }
                    reader.close();


                    int exitCode = process.waitFor();
                }


            System.out.println();
            System.out.print("> ");
        }
        sc.close();
    }
}