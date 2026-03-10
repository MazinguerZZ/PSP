package chat;

import java.io.*;
import java.net.*;
import javax.swing.*;

/**
 * ClienteChat es la clase principal del cliente de chat.
 * Permite conectarse a un servidor de chat, enviar y recibir mensajes en tiempo real
 * utilizando sockets y mostrar la interfaz gráfica usando Swing.
 * 
 * Flujo principal:
 * - Se muestra una ventana de configuración para obtener el usuario, host y puerto.
 * - Se establece la conexión con el servidor mediante un Socket.
 * - Se crea la ventana de chat en el Event Dispatch Thread.
 * - Se agrega un ActionListener al botón "Enviar" para enviar mensajes.
 * - Se inicia un hilo para recibir mensajes del servidor.
 * - Se registran recursos a cerrar automáticamente al salir del programa.
 *
 * Los mensajes enviados con destino -1 se envían a todos los clientes.
 * Se maneja la desconexión del servidor mostrando un mensaje en la ventana.
 */
public class ClienteChat {

    /**
     * Método principal que inicia el cliente de chat.
     *
     * @param args argumentos de línea de comando (no se utilizan)
     */
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        VentanaConfiguracion vc = new VentanaConfiguracion(frame);

        String usuario = vc.getUsuario();
        String host = vc.getHost();
        int puerto = vc.getPuerto();

        int id = (int) (Math.random() * 1000);

        try {
            Socket socket = new Socket(host, puerto);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            SwingUtilities.invokeLater(() -> {

                VentanaChat ventana = new VentanaChat(null);

                ventana.botonEnviar.addActionListener(e -> {
                    try {
                        String texto = ventana.campoMensaje.getText();
                        String destino = ventana.getDestinatario();

                        if (!texto.isEmpty()) {
                            out.writeObject(new MensajeChat(id, -1, usuario, texto));
                            ventana.mostrarMensaje("Yo: " + texto);
                            ventana.campoMensaje.setText("");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });

                ventana.setTitle("Chat - " + usuario);
                ventana.setVisible(true);

                new Thread(() -> {
                    try {
                        while (true) {
                            MensajeChat m = (MensajeChat) in.readObject();
                            ventana.mostrarMensaje(m.usuario + ": " + m.texto);
                        }
                    } catch (EOFException e) {
                        ventana.mostrarMensaje("Servidor desconectado.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }).start();
            });

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    out.close();
                    in.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar al servidor");
        }
    }
}
