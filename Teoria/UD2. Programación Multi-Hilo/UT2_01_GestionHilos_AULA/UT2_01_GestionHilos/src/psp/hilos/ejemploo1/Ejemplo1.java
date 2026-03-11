package psp.hilos.ejemploo1;

public class Ejemplo1 {
    public static void main(String[] args) {

        System.out.println("CLASE ^RINCIPAL coriendo. Kanzamiento de hilos...");
        System.out.println("Hilo de la clase Ejemoki1: " + Thread.currentThread().getName());

        HiloThread ht= new HiloThread();
        ht.start();

        new Thread(new HiloRunnable(), "HiloRunnable").start();

    }
}