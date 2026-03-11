package psp.hilos;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ContadorColaborativoLock {

    private static int value= 0;

    private static final int THREADS= 10;
    private static final int INCREMENTS_PER_THREAD= 100_000;

    private final Lock lock= new ReentrantLock();

    public void incrementa() {

       // lock.lock();

        try {
            if(lock.tryLock(1, TimeUnit.NANOSECONDS)) {

                int valorPrevio = value;

                value = value + 1;

                if (valorPrevio != (value - 1)) {
                    System.out.println("Valor previo: " + valorPrevio + " - Valor nuevo: " + value);
                }
            }
            else {
                System.out.println("El hilo no ha conseguido el cerrojo.");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {

        ContadorColaborativoLock ecc= new ContadorColaborativoLock();

        Thread[] threads= new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j= 0; j < INCREMENTS_PER_THREAD; j++) {
                    ecc.incrementa();
                }
            });

            threads[i].start();
        }

        for(Thread t: threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Esperado: " + (THREADS * INCREMENTS_PER_THREAD));
        System.out.println("Obtenido: " + value);
    }
}
