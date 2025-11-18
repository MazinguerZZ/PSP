package psp.comms;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Enumeration;

public class HostInfo {
    public static void main(String[] args) {

        try {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("Nombre del host local: " + localHost.getHostName());
            System.out.println("Direccion IP local: " + localHost.getHostAddress());

            InetAddress google = InetAddress.getByName("www.google.es");
            System.out.println("Nombre del host local: " + google.getHostName());
            System.out.println("Direccion IP local: " + google.getHostAddress());

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        try {
            //Enumeration = Va a recorrer el conjuntos de interfaces de red que tengo en mi pc
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();

                // Para recuperar el nombre de la interfaz
                System.out.println("Nombre de la interfaz: " + ni.getName());

                // Para decirnos si esta activa la interfaz
                System.out.println("Interfaz activa: " + ni.isUp());

                // Para recuperar la MAC que tiene cada interfaz
                System.out.println("MAC Address: " + Arrays.toString(ni.getHardwareAddress()));

                Enumeration<InetAddress> direcciones = ni.getInetAddresses();
                while (direcciones.hasMoreElements()) {
                    InetAddress ip = direcciones.nextElement();

                    // Para recuperar cada direccion ip que tiene cada interfaz
                    System.out.println("\tDirección IP: " + ip.getHostAddress());
                }

            }

        } catch (SocketException e) {
            throw new RuntimeException(e);
        }


    }
}