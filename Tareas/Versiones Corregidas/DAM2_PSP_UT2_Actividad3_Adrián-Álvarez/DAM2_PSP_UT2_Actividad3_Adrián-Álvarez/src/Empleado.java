import java.util.Random;
import java.util.concurrent.Semaphore;

public class Empleado extends Thread {
    private Semaphore puestos;
    private Semaphore ordenadores;
    private String nombre;
    private Random random = new Random();

    // Constantes de tiempo de trabajo aleatorio (en ms)
    private static final int MIN_TIEMPO_TRABAJO = 2000;
    private static final int MAX_TIEMPO_TRABAJO = 10000;

    // Constantes de tiempo de descanso aleatorio (en ms)
    private static final int MIN_TIEMPO_DESCANSO = 1000;
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
                        System.out.println(nombre + " no pudo reservar recursos, reintentando...");
                        Thread.sleep(1000);
                    }
                }

                int tiempoTrabajo = MIN_TIEMPO_TRABAJO + random.nextInt(MAX_TIEMPO_TRABAJO - MIN_TIEMPO_TRABAJO);
                System.out.println(nombre + " TRABAJANDO por " + (tiempoTrabajo / 1000) + " segundos...");
                Thread.sleep(tiempoTrabajo);

                liberarRecursos();

                int tiempoDescanso = MIN_TIEMPO_DESCANSO + random.nextInt(MAX_TIEMPO_DESCANSO - MIN_TIEMPO_DESCANSO);
                System.out.println(nombre + " DESCANSANDO por " + (tiempoDescanso / 1000) + " segundos...");
                Thread.sleep(tiempoDescanso);
            }

        } catch (InterruptedException e) {
            System.out.println(nombre + " fue interrumpido");
        }
    }

    /**
     * Intenta adquirir los dos recursos (puesto y ordenador) en orden aleatorio,
     * tal como indica el enunciado: "reservar una mesa y recoger un ordenador, o al revés".
     * Si se consigue el primero pero no el segundo, se libera el primero
     * para evitar interbloqueos.
     */
    private boolean intentarConseguirRecursos() {
        boolean primeroEsPuesto = random.nextBoolean();

        Semaphore primero     = primeroEsPuesto ? puestos     : ordenadores;
        Semaphore segundo     = primeroEsPuesto ? ordenadores : puestos;
        String nombrePrimero  = primeroEsPuesto ? "PUESTO"    : "ORDENADOR";
        String nombreSegundo  = primeroEsPuesto ? "ORDENADOR" : "PUESTO";

        if (primero.tryAcquire()) {
            System.out.println(nombre + " reservó " + nombrePrimero + ", intentando conseguir " + nombreSegundo + "...");
            if (segundo.tryAcquire()) {
                System.out.println(nombre + " reservó " + nombreSegundo + " — listo para trabajar");
                return true;
            } else {
                // No hay segundo recurso disponible: liberar el primero para no bloquear
                primero.release();
                System.out.println(nombre + " liberó " + nombrePrimero + " (no había " + nombreSegundo + " disponible)");
                return false;
            }
        }
        return false;
    }

    private void liberarRecursos() {
        puestos.release();
        ordenadores.release();
        System.out.println(nombre + " liberó puesto y ordenador");
    }
}