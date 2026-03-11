package psp.procesos;

import java.io.File;
import java.io.IOException;

public class EjecProceso {


    public void ejecutarPB (String comando) {
        ProcessBuilder pb;
        Process process;

        try{
            pb= new ProcessBuilder(comando);
            // pb= new ProcessBuilder("cmd", "/c", "ping -n 3 8.8.8.8");

            /* Para redirigir la salida del proceso a un fichero */
            // File log= new File("salida.log");
            // pb.redirectOutput(log);
            // pb.redirectError(log);

            process= pb.start();
            int retorno= process.waitFor();


            System.out.println("La ejecución de " + pb.command() + " devuelve " + retorno);
            System.out.println("Las variables de entorno del proceso son: " + pb.environment());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void ejecutarRun (String[] comando) {
        Runtime runtime;
        Process process;

        try{
            runtime= Runtime.getRuntime();
            process= runtime.exec(comando);

            synchronized (process) {
                process.wait(2000);
            }
            process.destroy();
        }
        catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args){
        String comando= args[0]; // args[0]; // "C:\\Program Files\\Adobe\\Acrobat DC\\Acrobat\\Acrobat.exe";
        EjecProceso ep= new EjecProceso();
        ep.ejecutarPB("taskmgr");

       // ep.ejecutarRun(args);
    }

}