package psp.hilos.Tarea1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Hilo> listaHilos = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.print("¿Cuantos triangulos quieres calcular? ");
        int numTriangulos = sc.nextInt();
        sc.nextLine();

        System.out.print("¿Quiere asignar prioridad al cálculo de los triángulos? (S o N) ");
        String prioridad = sc.nextLine();

        for(int i = 0; i < numTriangulos; i++){
            System.out.println("Triangulo " + (i + 1) + ":");

            System.out.print("Introduce la base: ");
            float base = sc.nextFloat();

            System.out.print("Introduce la altura: ");
            float altura = sc.nextFloat();

            Hilo hilo = new Hilo(base, altura, "Triangulo-" + (i + 1));

            if(prioridad.equals("S") || prioridad.equals("s")){
                System.out.print("Introduce la prioridad de 1 a 10: ");
                int numPrioridad = sc.nextInt();
                sc.nextLine();
                hilo.setPriority(numPrioridad);
            }

            listaHilos.add(hilo);
        }

        System.out.println("\n--- Calculando áreas ---");

        for (Hilo hilo : listaHilos) {
            hilo.start();
        }

        for (Hilo hilo : listaHilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        sc.close();
    }
}