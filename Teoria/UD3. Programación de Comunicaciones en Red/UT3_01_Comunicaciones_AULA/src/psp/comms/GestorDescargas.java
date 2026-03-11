package psp.comms;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class GestorDescargas {

    public void descargarArchivo(String url_descargar, String nombreFichero) {
        System.out.println("Descargando : " + url_descargar);

        InputStream is= null;
        BufferedReader bReader= null;
        FileWriter escritorFichero= null;
        try {
            URL url= new URL(url_descargar);
            is= url.openStream();
            bReader= new BufferedReader(new InputStreamReader(is));
            escritorFichero= new FileWriter(nombreFichero);

            String linea;
            while((linea= bReader.readLine()) != null) {
                escritorFichero.write(linea + "\n");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {

            try {
                escritorFichero.close();
                bReader.close();
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static void main(String[] args) {
        GestorDescargas gd= new GestorDescargas();
        String url= "https://www.bbc.com/robots.txt";
        gd.descargarArchivo(url, "descarga.txt");
    }

}
