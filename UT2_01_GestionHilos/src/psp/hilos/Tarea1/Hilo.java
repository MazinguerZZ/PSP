package psp.hilos.Tarea1;

import java.util.Scanner;

public class Hilo extends Thread{

    Scanner sc = new Scanner(System.in);

    @Override
    public void run() {
        System.out.print("Cuantos triangulos quieres calcular: ");
        int numTriangle = sc.nextInt();

        for (int i = 0; i <= numTriangle; i++) {
            System.out.print("Triangulo " + i + ": ");

            System.out.print("Introduce la base: ");
            int base = sc.nextInt();

            System.out.print("Introduce la altura: ");
            float altura  = sc.nextFloat();

            System.out.print("Introduce la prioridad del 1 al 10: ");
            int prioridad = sc.nextInt();
        }

        while (sc.equals("N")) {
            System.out.println("");
        }
    }
}
