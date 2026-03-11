package psp.comms.clienteservidorudp;

import java.io.IOException;
import java.net.*;

public class ClientUDP {

    public static void main(String[] args) {

        int PUERTO= 6789;
        int BUFFER_SIZE= 1000;
        System.out.println("Soy el cliente y voy a enviar un datagrama.");

        String mensaje= "Hola soy un cliente UDP";

        try (DatagramSocket socketUDP= new DatagramSocket()) {
            byte[] men= mensaje.getBytes();
            InetAddress hostServidor= InetAddress.getByName("localhost");

            DatagramPacket peticion= new DatagramPacket(men, men.length,hostServidor, PUERTO);
            socketUDP.send(peticion);

            byte[] bufer= new byte[BUFFER_SIZE];
            DatagramPacket respuesta= new DatagramPacket(bufer, BUFFER_SIZE);
            socketUDP.receive(respuesta);

            System.out.println("Respuesta: " + new String(respuesta.getData()).trim());

        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
