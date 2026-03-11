package psp.hilos.ejemploo1;

public class HiloRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Ejecutando hilo 2 (implementa la clase Runnable)");
        System.out.println("Nombre del hilo actual: " + Thread.currentThread().getName());
    }

}
