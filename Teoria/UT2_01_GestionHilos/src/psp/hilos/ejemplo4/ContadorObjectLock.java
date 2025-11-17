package psp.hilos.ejemplo4;

public class ContadorObjectLock {

    private static final int THREADS = 10;
    private static final int INCREMENTS_PER_THREAD = 100_000;

    public int contador1 = 0;
    public int contador2 = 0;

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void incrementar1() {
        synchronized (lock1) {
            contador1++;
        }
    }

    public void incrementar2() {
        synchronized (lock2) {
            contador2++;
        }
    }

    public static void main(String[] args) {
        ContadorObjectLock contador = new ContadorObjectLock();

        Thread[] threads = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS_PER_THREAD; j++) {
                    contador.incrementar1();
                    contador.incrementar2();

                }
            });

            threads[i].start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            }catch(InterruptedException e){
                throw new RuntimeException();
            }
        }

        System.out.println("Esperando: " + (THREADS * INCREMENTS_PER_THREAD));
        System.out.println("Obtenido contador1: " + contador.contador1);
        System.out.println("Obtenido contador2: " + contador.contador2);


    }


}
