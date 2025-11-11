package psp.hilos.Filosofos;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Filosofo implements Runnable{

    private final Semaphore cubiertoIzqo, cubiertoDcho;

    public Filosofo(Semaphore cubiertoIzqo, Semaphore cubiertoDcho) {
        this.cubiertoIzqo = cubiertoIzqo;
        this.cubiertoDcho = cubiertoDcho;
    }

    @Override
    public void run() {

        Random random = new Random();

        while (true) {
            try {
                /*
                cubiertoIzqo.acquire();
                cubiertoDcho.acquire();
                System.out.println(Thread.currentThread().getName() + " comiendo...");
                cubiertoIzqo.release();
                cubiertoDcho.release();
                 */

                cubiertoIzqo.acquire();
                if (cubiertoDcho.tryAcquire()){
                    System.out.println(Thread.currentThread().getName() + " comiendo...");
                    cubiertoDcho.release();
                }
                else {
                    System.out.println(Thread.currentThread().getName() + " no ha podido coger el cubierto y suelta el otro.");
                }
                cubiertoIzqo.release();

                System.out.println(Thread.currentThread().getName() + " pensando...");
                Thread.sleep(random.nextInt(1, 5));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }

}
