package psp.procesos.sumas;

import java.io.*;

public class LanzadorSumas {

    private static String FILENAME = "logsumasglobal.txt";

    private void lanzarSuma(Integer n1, Integer n2, Integer proceso) {

        ProcessBuilder pb;
        Process process;

        String classname = "psp.procesos.sumas.Sumador";
        // String classpath = "/home/alumno/IdeaProjects/Lanzador Sumas/out/production/LanzadorSumas/";
        String currentPath = System.getProperty("user.dir");
        String classpath = currentPath + "/out/production/Lanzador Sumas/";

        try {

            pb = new ProcessBuilder("java" , "-cp" , classpath, classname, n1.toString(), n2.toString(), proceso.toString(), FILENAME);

            pb.inheritIO();

            process = pb.start();

            // process.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        try {
            File file = new File(FILENAME);
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        LanzadorSumas ls = new LanzadorSumas();

        // ls.lanzarSuma(20000, 355555, 1);
        // ls.lanzarSuma(23, 31, 2);
        ls.lanzarSuma(2, 3  , 3);
        // ls.lanzarSuma(233, 3224, 4);
        // ls.lanzarSuma(21, 43, 5);

        try {
            BufferedReader br = new BufferedReader(new FileReader(FILENAME));
            String output, line;
            output ="";

            while ((line=br.readLine()) != null){
                output += line;
            }

            System.out.println(output);
            br.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
