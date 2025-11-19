package psp.comms.clienteservidor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {

        ServerSocket servidor = null;
        Socket cliente = null;
        int numCliente = 0;
        int PUERTO = 5000;

        try {
            servidor = new ServerSocket(PUERTO);

            System.out.println("Soy el servidor y empiezo a escuchar por el puerto: " + PUERTO);

            do {

                cliente = servidor.accept();
                numCliente++;
                System.out.println("\tLlega el cliente: "+ numCliente);

                DataOutputStream os = new DataOutputStream(cliente.getOutputStream());
                os.writeUTF("Usted es mi cliente: " + numCliente);

                cliente.close();
                System.out.println("Se ha cerrado la conexión con el cliente: " + numCliente);

            } while (true);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                servidor.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
