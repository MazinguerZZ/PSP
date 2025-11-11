package psp.procesos;

import java.io.File;
import java.io.IOException;

public class EjeProceso {

    public void EjecutarPB (String comando) {
        ProcessBuilder pb;
        Process process;

        try {
            // pb = new ProcessBuilder(comando);
            pb = new ProcessBuilder("bash", "-c", "ifconfig");

            File log = new File("/home/alumno/salida.log");
            pb.redirectOutput(log);
            pb.redirectError(log);

            process=pb.start();
            int retorno = process.waitFor();

            System.out.println("La ejecucion de " + pb.command() + " devuelve " + retorno);
            System.out.println("Las variables de entorno del proceso son: " + pb.environment());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void ejecutarRun (String[] comando) {
        Runtime runtime;
        Process process;

        try {
            runtime = Runtime.getRuntime();
            process = runtime.exec(comando);

            synchronized (process) {
                process.wait(2000);
            }
            process.destroy();
        }
        catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        // String comando= "/usr/bin/google-chrome";
        EjeProceso ep = new EjeProceso();
        ep.EjecutarPB("/usr/bin/google-chrome");

        //ep.ejecutarRun(args);
    }

}