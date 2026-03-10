import java.util.Random;
import java.util.concurrent.Semaphore;

public class Empleado extends Thread {
    private Semaphore puestos;
    private Semaphore ordenadores;
    private String nombre;
    private Random random = new Random();

    private static final int MAX_TIEMPO_TRABAJO = 10000;
    private static final int MAX_TIEMPO_DESCANSO = 5000;

    public Empleado(Semaphore puestos, Semaphore ordenadores, String nombre) {
        this.puestos = puestos;
        this.ordenadores = ordenadores;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(nombre + " llega a trabajar");

                boolean recursosObtenidos = false;

                while (!recursosObtenidos) {
                    recursosObtenidos = intentarConseguirRecursos();
                    if (!recursosObtenidos) {
                        Thread.sleep(1000);
                    }
                }

                int tiempoTrabajo = random.nextInt(MAX_TIEMPO_TRABAJO) + 2000;
                System.out.println(nombre + " TRABAJANDO por " + (tiempoTrabajo/1000) + " segundos...");
                Thread.sleep(tiempoTrabajo);

                liberarRecursos();

                int tiempoDescanso = random.nextInt(MAX_TIEMPO_DESCANSO) + 1000;
                System.out.println(nombre + " DESCANSANDO por " + (tiempoDescanso/1000) + " segundos...");
                Thread.sleep(tiempoDescanso);
            }

        } catch (InterruptedException e) {
            System.out.println(nombre + " fue interrumpido");
        }
    }

    private boolean intentarConseguirRecursos() throws InterruptedException {
        if (puestos.tryAcquire() && ordenadores.tryAcquire()) {
            System.out.println(nombre + " reservó PUESTO y ORDENADOR");
            return true;
        } else {
            if (puestos.availablePermits() < puestos.getQueueLength()) {
                puestos.release();
            }
            if (ordenadores.availablePermits() < ordenadores.getQueueLength()) {
                ordenadores.release();
            }
            return false;
        }
    }

    private void liberarRecursos() {
        puestos.release();
        ordenadores.release();
        System.out.println(nombre + " liberó puesto y ordenador");
    }
}