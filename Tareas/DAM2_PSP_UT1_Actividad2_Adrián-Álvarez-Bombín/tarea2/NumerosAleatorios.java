package tarea2;

import java.util.Random;

public class NumerosAleatorios {
    public static void main(String[] args) {
        Random random = new Random();
        int numero = random.nextInt(10);
        System.out.print(numero);
    }
}
