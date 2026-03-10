
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase main para ejecutar el codigo y proporcionar
 * las entradas del usuario y manejo del codigo para la salida
 */
public class Main {

    static Scanner sc = new Scanner(System.in);


    /**
     * Metodo main para ejecutar el programa con un for que ejecuta
     * de la ip 1 a la 254 en un rango definido con iniciando 254 hilos
     * @param args
     * @throws InterruptedException lanza esta excepcion en caso de que surja
     * con algun hilo
     */
    public static void main(String[] args) throws InterruptedException {

        /* Capturar entrada del usuario para conseguir la ip de subred */
        System.out.print("Introduce una subred: con formato (X.X.X): ");
        String ipSubred = sc.nextLine();


        /* Validacion de la ip de subred con el metodo */
        if (!validarSubred(ipSubred)) {
            System.out.println("Subred inválida");
            return;
        }

        List<PuertosActivos> hilosPuertos = new ArrayList<>();
        Thread[] hilosPing = new Thread[254];

        for (int i = 1; i <= 254; i++) {
            final String ip = ipSubred + "." + i;

            hilosPing[i - 1] = new Thread(() -> {
                if (hostActivos(ip)) {
                    PuertosActivos hilo = new PuertosActivos(ip); // Crear los hilos de la clase PuertosActivos
                    /* Synchronized para evitar condiciones de carrera cada un solo hilo pueda añadir su objeto
                    a la lista compartida  */
                    synchronized (hilosPuertos) {
                        hilosPuertos.add(hilo);
                    }
                    hilo.start();
                }
            });

            hilosPing[i - 1].start();
        }

        // Esperamos a que terminen los hilos de ping
        for (Thread t : hilosPing) {
            t.join();
        }

        // Esperamos a que terminen los hilos de puertos
        for (PuertosActivos hilo : hilosPuertos) {
            hilo.join();
        }

        /* Syso para comprobar que se acabo de escanear */
        System.out.println("<------ Escaneo terminado ------>");
    }

    /**
     * Metodo para comprobar que host estan activos en la subred
     * mediante el ping 
     * 
     * ** Esto detecta si es SO es windows o Linux **
     * 
     * @param ip dirrecion que comprueba el metodo
     * @return devuelve true si la ping responde sino false
     */

    private static boolean hostActivos(String ip) {
        String os = System.getProperty("os.name").toLowerCase();
        ProcessBuilder pb = null;

        if (os.contains("win")) {
            pb = new ProcessBuilder("ping", "-n", "1", ip);
        } else if(os.contains("linux")) {
            pb = new ProcessBuilder("ping", "-c", "1", ip);
        }

        try {
            Process proceso = pb.start();
            int estado = proceso.waitFor(); // 0 = éxito, distinto de 0 = fallo
            return (estado == 0);
        } catch (IOException | InterruptedException e) {
            return false;
        }
    }

    /**
     * Metodo proporcionado por el profesor implementacion
     * para validar la subred
     * @param ip de la subred introducida por el usuario
     * @return devuelve true si la subred es true, false en caso contrario que no lo fuera
     */
    private static boolean validarSubred(String ip) {
        String[] partes = ip.split("\\.");
        if (partes.length != 3) {
            return false;
        }

        for (String part : partes) {
            try {
                int num = Integer.parseInt(part);
                if (num < 0 || num > 255) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
}
