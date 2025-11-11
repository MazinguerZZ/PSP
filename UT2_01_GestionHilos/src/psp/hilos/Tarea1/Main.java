package psp.hilos.Tarea1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /* 5 hilos generados */
        List<Hilo> listaHilos = new ArrayList<>();

        Scanner sc = new Scanner(System.in);


        System.out.print("¿Cuantos triangulos quieres calcular?' ");
        int numTriangulos = sc.nextInt();
        sc.nextLine();

        System.out.print("¿Quiere asignar prioridad al cálculo de los triángulos? (S o N) ");
        String prioridad = sc.nextLine();

        Boolean esPrioridad = prioridad.equals("S");

        for(int i = 1; i <= numTriangulos; i++){

            listaHilos.add(new Hilo());

            System.out.print("Triangulo " + i + ":");
            System.out.println("\n");
            System.out.print("Introduce la base: ");
            float base = sc.nextFloat();

            System.out.print("Introduce la altura: ");
            float altura = sc.nextFloat();

            if(esPrioridad){
                System.out.print("Introduce la prioridad de 1 a 10: ");
                int numPrioridad = sc.nextInt();

            }

            try{
                for (Hilo hilo : listaHilos) { /* hilo hilo hilo no diferencial = = =  5*/
                    new Hilo().start();
                }}catch(IllegalThreadStateException e){
                e.printStackTrace();
            }


        }

    }
}
