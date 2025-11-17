package psp.hilos.ejemplo1;

public class Ejemplo1 {
    public static void main(String[] args) {

        System.out.println("CLASE PRINCIPAL corriendo. Lanzamiento de hilos...");
        System.out.println("Hilo de la clase Ejemplo1: " + Thread.currentThread().getName());

        HiloThread ht = new HiloThread();
        ht.start();

        new Thread(new HiloRunnable(), "HiloRunnable").start();


    }
}