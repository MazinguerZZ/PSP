package psp.hilos.ejemplo2;

public class HiloNombreContador extends Thread {

    @Override
    public void run() {

        for(int contador= 1; contador<=150; contador++) {
            System.out.println("Hilo " + getName()
                + " - contador: " + contador);
            try {
                sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }

    }

}
