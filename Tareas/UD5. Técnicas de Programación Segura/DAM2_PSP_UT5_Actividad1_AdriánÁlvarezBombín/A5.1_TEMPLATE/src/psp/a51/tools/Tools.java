package psp.a51.tools;

public class Tools {

    public static class UtilidadesSistema {

        private static String os = System.getProperty("os.name").toLowerCase();

        public static boolean isLinux(){
            //System.out.println(os);
            return os.contains("linux");
        }

        public static boolean isWindows(){
            //System.out.println(os);
            return os.contains("win");
        }

    }
}
