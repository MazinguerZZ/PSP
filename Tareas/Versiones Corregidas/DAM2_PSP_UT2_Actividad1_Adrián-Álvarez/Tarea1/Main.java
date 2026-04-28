package psp.hilos.Tarea1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        List<Hilo> listaHilos = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        int numTriangulos = 0;
        boolean entradaValida = false;

        while(!entradaValida) {
            try {
                System.out.print("¿Cuantos triangulos quieres calcular? ");
                numTriangulos = sc.nextInt();
                entradaValida = true;
            } catch(InputMismatchException e) {
                System.out.println("Error: introduce un número entero válido.");
                sc.nextLine();
            }
        }
        sc.nextLine();

        System.out.print("¿Quiere asignar prioridad al cálculo de los triángulos? (S o N) ");
        String prioridad = sc.nextLine();

        for(int i = 0; i < numTriangulos; i++){
            System.out.println("Triangulo " + (i + 1) + ":");

            float base = 0;
            entradaValida = false;
            while(!entradaValida) {
                try {
                    System.out.print("Introduce la base: ");
                    base = sc.nextFloat();
                    entradaValida = true;
                } catch(InputMismatchException e) {
                    System.out.println("Error: introduce un número válido.");
                    sc.nextLine();
                }
            }

            float altura = 0;
            entradaValida = false;
            while(!entradaValida) {
                try {
                    System.out.print("Introduce la altura: ");
                    altura = sc.nextFloat();
                    entradaValida = true;
                } catch(InputMismatchException e) {
                    System.out.println("Error: introduce un número válido.");
                    sc.nextLine();
                }
            }

            Hilo hilo = new Hilo(base, altura, "Triangulo-" + (i + 1));

            if(prioridad.equals("S") || prioridad.equals("s")){
                entradaValida = false;
                while(!entradaValida) {
                    try {
                        System.out.print("Introduce la prioridad de 1 a 10: ");
                        int numPrioridad = sc.nextInt();
                        if(numPrioridad >= 1 && numPrioridad <= 10) {
                            hilo.setPriority(numPrioridad);
                            entradaValida = true;
                        } else {
                            System.out.println("Error: la prioridad debe estar entre 1 y 10.");
                        }
                    } catch(InputMismatchException e) {
                        System.out.println("Error: introduce un número entero válido.");
                        sc.nextLine();
                    }
                }
                sc.nextLine();
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