package psp.comms;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class GestorDescargas {

    public void descargarArchivo(String url_descargar, String nombreFichero) {
        System.out.println("Descargando: " + url_descargar);


        try {
            URL url = new URL(url_descargar);

            // InputStream para ir leyendo la url
            InputStream is = url.openStream();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(is));
            //El FileWriter va a escribir todo lo que lea de la url
            FileWriter escritorFichero = new FileWriter(nombreFichero);

            String linea;
            while ((linea = bReader.readLine()) != null) {
                escritorFichero.write(linea + "\n");
            }
            escritorFichero.close();
            bReader.close();
            is.close();



        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // Llamar al objeto y realizar la llamada
    public static void main(String[] args) {
        GestorDescargas gd = new GestorDescargas();
        String url = "https://www.bbc.com/robots.txt";
        gd.descargarArchivo(url, "descarga.txt");
    }
}
