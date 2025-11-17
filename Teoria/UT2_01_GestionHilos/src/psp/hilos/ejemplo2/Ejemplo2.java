package psp.hilos.ejemplo2;

public class Ejemplo2 {
    public static void main(String[] args) {

        HiloNombreContador hilo1, hilo2, hilo3;
        hilo1 = new HiloNombreContador();
        hilo1.setName("Mi hilo 1");
        hilo2 = new HiloNombreContador();
        hilo2.setName("Mi hilo 2");
        hilo3 = new HiloNombreContador();
        hilo3.setName("Mi hilo 3");

        System.out.println("Comienza la ejecucion...");

        hilo1.setPriority(Thread.MIN_PRIORITY);
        hilo2.setPriority(Thread.MAX_PRIORITY);
        hilo3.setPriority(Thread.NORM_PRIORITY);

        hilo1.start();
        hilo2.start();
        hilo3.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Finalizada la ejecucion de los hilos.");
    }
}
