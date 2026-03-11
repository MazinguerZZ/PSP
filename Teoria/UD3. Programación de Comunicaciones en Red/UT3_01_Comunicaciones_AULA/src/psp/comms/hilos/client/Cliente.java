package psp.comms.hilos.client;

import psp.comms.hilos.model.Ejemplo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

    public static void main(String[] args) {
        InetAddress direccion;
        Socket servidor= null;
        int PUERTO= 5000;

        System.out.println("Soy un cliente e intento conectarme");
        ObjectOutputStream bufferSalidaDatos= null;
        ObjectInputStream bufferEntradaDatos= null;

        try {
            direccion= InetAddress.getLocalHost();
            servidor= new Socket(direccion, PUERTO);
            System.out.println("Conexión realizado con éxito");

            bufferSalidaDatos= new ObjectOutputStream(servidor.getOutputStream());
            Ejemplo datosSalida= new Ejemplo("Objeto enviado por el cliente", 1);
            bufferSalidaDatos.writeObject(datosSalida);
            bufferSalidaDatos.flush();
            System.out.println("Enviado al servidor el objeto " + datosSalida.toString());

            Thread.sleep(10000);

            bufferEntradaDatos= new ObjectInputStream(servidor.getInputStream());
            Ejemplo datosEntrada= (Ejemplo)bufferEntradaDatos.readObject();
            datosEntrada.mostrar();
            System.out.println("Recibido del servidor el objeto " + datosEntrada.toString());

            //System.out.println(datos.readUTF());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                assert bufferEntradaDatos != null;
                bufferEntradaDatos.close();
                bufferSalidaDatos.close();
                if (servidor != null) {
                    servidor.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
