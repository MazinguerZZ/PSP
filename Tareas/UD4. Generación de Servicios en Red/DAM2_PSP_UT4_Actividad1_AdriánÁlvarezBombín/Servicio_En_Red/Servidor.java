
import java.io.*;
import java.net.*;

/**
 * Servidor simple que ofrece dos servicios en red sobre textos: 1. Conteo de
 * palabras. 2. Reemplazo de palabras.
 *
 * Funciona de forma concurrente, atendiendo varios clientes al mismo tiempo.
 */
public class Servidor implements Runnable {

    private Socket clienteSocket;
    public static final int PUERTO = 5000;

    /**
     * Constructor que recibe el socket de un cliente para atenderlo.
     *
     * @param clienteSocket Socket de conexión con el cliente
     */
    public Servidor(Socket clienteSocket) {
        this.clienteSocket = clienteSocket;
    }

    /**
     * Método principal que inicia el servidor y espera conexiones de clientes.
     * Por cada cliente crea un hilo independiente.
     *
     * @param args No se utilizan argumentos
     */
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado en el puerto " + PUERTO);

            while (true) {
                Socket cliente = serverSocket.accept();
                // Crear un hilo por cliente
                new Thread(new Servidor(cliente)).start();
            }

        } catch (IOException e) {
            System.out.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }

    /**
     * Método que se ejecuta en el hilo para atender a un cliente. Recibe la
     * petición, procesa el servicio solicitado y devuelve el resultado.
     */
    @Override
    public void run() {
        try (
                BufferedReader entrada = new BufferedReader(
                        new InputStreamReader(clienteSocket.getInputStream())); PrintWriter salida = new PrintWriter(
                        clienteSocket.getOutputStream(), true)) {
            String mensaje = entrada.readLine();
            if (mensaje == null || mensaje.isEmpty()) {
                salida.println("Error: mensaje vacío");
                return;
            }

            String[] datos = mensaje.split("\\|");
            String servicio = datos[0];

            // Servicio de conteo de palabras
            if (servicio.equalsIgnoreCase("COUNT") && datos.length >= 3) {
                String palabra = datos[1];
                String texto = datos[2];
                int resultado = contarPalabra(palabra, texto);
                salida.println(resultado);

                // Servicio de reemplazo de palabras
            } else if (servicio.equalsIgnoreCase("REPLACE") && datos.length >= 4) {
                String palabraOriginal = datos[1];
                String palabraNueva = datos[2];
                String texto = datos[3];
                String resultado = texto.replaceAll("(?i)\\b" + palabraOriginal + "\\b", palabraNueva);
                salida.println(resultado);

            } else {
                salida.println("Error: formato de mensaje incorrecto");
            }

        } catch (IOException e) {
            System.out.println("Error al atender a un cliente: " + e.getMessage());
        }
    }

    /**
     * Cuenta cuántas veces aparece una palabra en un texto. Ignora
     * mayúsculas/minúsculas y signos de puntuación.
     *
     * @param palabra La palabra a buscar
     * @param texto El texto donde buscar
     * @return Número de apariciones de la palabra
     */
    private int contarPalabra(String palabra, String texto) {
        int contador = 0;
        // Separar palabras por cualquier caracter que no sea letra o número
        String[] palabras = texto.split("\\W+");
        for (String p : palabras) {
            if (p.equalsIgnoreCase(palabra)) {
                contador++;
            }
        }
        return contador;
    }
}
