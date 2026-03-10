package chat;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * ServidorChat es la clase principal del servidor de chat.
 * Mantiene conexiones con múltiples clientes y permite enviar mensajes entre ellos.
 *
 * Cada cliente se identifica mediante un ID único. Los mensajes con destino -1 se envían
 * a todos los clientes conectados. El servidor utiliza hilos para manejar cada cliente
 * de forma independiente y mantener la comunicación en tiempo real.
 */
public class ServidorChat {

    /**
     * Puerto en el que el servidor escucha conexiones entrantes.
     */
    public static final int PUERTO = 5000;

    /**
     * Mapa que relaciona los IDs de clientes con sus ObjectOutputStream
     * para enviar mensajes.
     */
    private static Map<Integer, ObjectOutputStream> clientes = new HashMap<>();

    /**
     * Contador que asigna un ID único a cada cliente que se conecta.
     */
    private static int id = 0;

    /**
     * Método principal que inicia el servidor de chat.
     *
     * @param args argumentos de línea de comando (no se utilizan)
     */
    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado");

            while (true) {
                Socket s = servidor.accept();
                id++;
                ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
                clientes.put(id, out);

                new Thread(() -> manejarCliente(s, id)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Maneja la comunicación con un cliente específico.
     * Recibe mensajes del cliente y los distribuye a los destinatarios correspondientes.
     *
     * @param s Socket del cliente
     * @param idCliente ID único del cliente
     */
    private static void manejarCliente(Socket s, int idCliente) {
        try {
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());

            while (true) {
                MensajeChat msg = (MensajeChat) in.readObject();

                for (int id : clientes.keySet()) {
                    if ((msg.destino == -1 && id != msg.origen) || msg.destino == id) {
                        clientes.get(id).writeObject(msg);
                    }
                }
            }
        } catch (Exception e) {
            clientes.remove(idCliente);
            System.out.println("Cliente desconectado");
        }
    }
}
