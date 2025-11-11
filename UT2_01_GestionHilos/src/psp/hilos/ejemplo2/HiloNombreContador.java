package psp.hilos.ejemplo2;

public class HiloNombreContador extends Thread{

    @Override
    public void run() {
        for (int contador= 0; contador <= 150; contador++) {
            System.out.println("Hilo " + getName() + " - contador: " + contador);

            try {
                sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
