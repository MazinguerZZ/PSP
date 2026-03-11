package psp.hilos.filosofos;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Filosofo implements Runnable {

    private final Semaphore cubiertoIzdo, cubiertoDcho;

    public Filosofo(Semaphore cubiertoIzdo, Semaphore cubiertoDcho) {
        this.cubiertoIzdo= cubiertoIzdo;
        this.cubiertoDcho= cubiertoDcho;
    }

    @Override
    public void run() {

        Random random= new Random();

        while(true) {
            try {
                /*
                cubiertoIzdo.acquire();
                cubiertoDcho.acquire();
                System.out.println(Thread.currentThread().getName() + " comiendo...");
                cubiertoIzdo.release();
                cubiertoDcho.release();
                 */

                cubiertoIzdo.acquire();
                if(cubiertoDcho.tryAcquire()) {
                    System.out.println(Thread.currentThread().getName() + " comiendo...");
                    cubiertoDcho.release();
                }
                else{
                    System.out.println(Thread.currentThread().getName() + "no ha podido coger el cubierto y suelta el otro.");
                }
                cubiertoIzdo.release();

                System.out.println(Thread.currentThread().getName() + " pensando...");
                Thread.sleep(random.nextInt(1, 5));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }

}
