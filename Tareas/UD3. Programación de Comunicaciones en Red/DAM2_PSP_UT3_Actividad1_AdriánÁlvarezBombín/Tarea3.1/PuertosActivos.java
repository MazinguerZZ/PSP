
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class PuertosActivos extends Thread {
    private final int NUM_PUERTOS = 5000;
    private final String ip;
    private final Map<Integer, String> servicios;

    // Puertos comunes a escanear
    private static final int[] puertosComunes = {
        21, 22, 23, 25, 53, 80, 110, 135, 139, 143, 443, 445, 3389
    };

    /**
     *
     * @param ip devuelve la ip
     */
    public PuertosActivos(String ip) {
        this.ip = ip;
        this.servicios = cargarServicios(); // aquí se ejecuta todo el código de nuestro metodo 
    }

    /**
     * Carga los servicios conocidos desde el fichero remoto de IANA.
     *
     * Se procesan las líneas del fichero y se construye un mapa con los puertos
     * bien conocidos (0–1023) y su servicio asociado.
     *
     *
     * @return un mapa con pares puerto → servicio.
     */
    private Map<Integer, String> cargarServicios() {
        Map<Integer, String> mapa = new HashMap<>();
        try {
            URL url = new URL("http://ftp.sun.ac.za/ftp/pub/documentation/security/port-numbers.txt");
            URLConnection connection = url.openConnection();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                     // Si la línea está vacía o es un comentario (empieza por #) se salta
                    if (line.isEmpty() || line.startsWith("#")) {
                        continue;
                    }

                    // Buscamos las lineas que contenga tcp
                    if (line.contains("/tcp")) {
                        // Divimos la linea en partes separadas con espacios \\s+
                        String[] partes = line.split("\\s+");
                        if (partes.length >= 2) {
                            // La primera parte hace referencia al nombre del servicio
                            String servicio = partes[0];
                            // La segunda parte hace referencia al numero de puerto
                            String puertoStr = partes[1].split("/")[0];

                            if (puertoStr.matches("\\d+")) {
                                // Solo añadimos puertos bien conocidos (0–NUM_PUERTOS)
                                int puerto = Integer.parseInt(puertoStr);
                                if (puerto >= 0 && puerto <= NUM_PUERTOS) {
                                    mapa.put(puerto, servicio);
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error cargando lista de servicios: " + e.getMessage());
        }
        return mapa;
    }

    /**
     * Escanea los puertos comunes de la IP indicada.
     * 
     * Muestra por consola los puertos abiertos junto con el servicio asociado.
     * Si ocurre un error, se asume que el puerto está cerrado.
     * </p>
     */
    @Override
    public void run() {
        System.out.println("IP " + ip + " ACTIVA");

        for (int puerto : puertosComunes) {
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress(ip, puerto), 50);
                String servicio = servicios.getOrDefault(puerto, "desconocido");
                System.out.println("\tPuerto " + puerto + " ABIERTO: " + servicio);
            } catch (IOException e) {
                // Damos por hecho que si hay algun error el puerto esta cerrado
            }
        }
    }
}
