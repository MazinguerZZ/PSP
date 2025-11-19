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

                // PAra limpiar y que no salgan las que no se usan
                // if (!ni.isLoopback() && ni.isUp() && ni.getInterfaceAddresses().isEmpty()) {

                    // Para recuperar el nombre de la interfaz
                    System.out.println("Nombre de la interfaz: " + ni.getName());

                    // Para decirnos si esta activa la interfaz
                    System.out.println("Interfaz activa: " + ni.isUp());

                    // Para recuperar la MAC que tiene cada interfaz, y convertirlo a hexadecimal
                    byte[] MACbytes = ni.getHardwareAddress();
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < MACbytes.length; i++) {
                        // En la expresion regular, hace: la X es para pasarlo a hexadecimal, y el 02 es para que tenga una
                        // logitud de 2 y lo que falte se rellene con 0
                        sb.append(String.format("%02X", MACbytes[i]));
                        if (i < MACbytes.length - 1) {
                            sb.append(":");
                        }
                    }
                    System.out.println("MAC Address: " + sb);


                    Enumeration<InetAddress> direcciones = ni.getInetAddresses();
                    while (direcciones.hasMoreElements()) {
                        InetAddress ip = direcciones.nextElement();

                        // Para recuperar cada direccion ip que tiene cada interfaz
                        System.out.println("\tDirección IP: " + ip.getHostAddress());
                    }

                }
            // }

        } catch (SocketException e) {
            throw new RuntimeException(e);
        }


    }
}