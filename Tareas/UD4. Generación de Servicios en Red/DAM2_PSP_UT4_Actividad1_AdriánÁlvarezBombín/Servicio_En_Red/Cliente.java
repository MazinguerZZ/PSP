import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Cliente que se conecta a un servidor para solicitar servicios de texto:
 * - Conteo de palabras
 * - Reemplazo de palabras
 *
 * Permite seleccionar el servicio, enviar los parámetros y mostrar el resultado.
 */
public class Cliente {

    // Dirección del servidor (localhost si se ejecuta en la misma máquina)
    public static final String HOST = "localhost";
    public static final int PUERTO = 5000;

    /**
     * Metodo principal del cliente.
     * Muestra un menu, solicita los datos al usuario, envía la petición al servidor
     * y muestra la respuesta recibida
     *
     * @param args No se usan argumentos
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (Socket socket = new Socket(HOST, PUERTO);
             BufferedReader entrada = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

            // Menú para que el usuario seleccione el servicio
            System.out.println("Seleccione el servicio:");
            System.out.println("1. Contar palabras");
            System.out.println("2. Reemplazar palabras");
            System.out.print("Opción: ");

            int opcion = sc.nextInt();
            sc.nextLine(); 

            String mensaje = "";

            if (opcion == 1) {
                // Servicio de conteo de palabras
                System.out.print("Palabra a buscar: ");
                String palabra = sc.nextLine().trim();

                System.out.print("Texto: ");
                String texto = sc.nextLine().trim();

                mensaje = "COUNT|" + palabra + "|" + texto;

            } else if (opcion == 2) {
                // Servicio de reemplazo de palabras
                System.out.print("Palabra original: ");
                String original = sc.nextLine().trim();

                System.out.print("Palabra nueva: ");
                String nueva = sc.nextLine().trim();

                System.out.print("Texto: ");
                String texto = sc.nextLine().trim();

                mensaje = "REPLACE|" + original + "|" + nueva + "|" + texto;

            } else {
                System.out.println("Opción no válida. Saliendo...");
                return;
            }

            // Enviar la solicitud al servidor
            salida.println(mensaje);

            // Recibir y mostrar el resultado
            String resultado = entrada.readLine();
            System.out.println("\nResultado del servidor:");
            System.out.println(resultado);

        } catch (UnknownHostException e) {
            System.out.println("No se pudo conectar al servidor: host desconocido");
        } catch (IOException e) {
            System.out.println("Error de comunicación con el servidor " );
        } finally {
            sc.close();
        }
    }
}
