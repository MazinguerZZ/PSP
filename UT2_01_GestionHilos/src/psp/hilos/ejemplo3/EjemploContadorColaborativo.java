package psp.hilos.ejemplo3;

public class EjemploContadorColaborativo {

    private static int value = 0;

    private static final int THREADS = 10;
    private static final int INCREMENTS_PER_THREAD = 100_000;

    synchronized public void implementa() {

        int valorPrevio = value;

        value = value + 1;

        if (valorPrevio != (value-1)) {
            System.out.println("Valor previo: " + valorPrevio + " - Valor nuevo: " + value);
        }
    }

    public static void main(String[] args) {

        EjemploContadorColaborativo ecc = new EjemploContadorColaborativo();

        Thread[] threads = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS_PER_THREAD; j++) {
                    ecc.implementa();
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
        System.out.println("Obtenido: " + value);
    }
}
