import java.util.Random;

public class Cliente extends Thread {

    // Constantes configurables
    private static final int MAX_OPERACIONES = 5;
    private static final double MAX_CANTIDAD  = 200.0;
    private static final int MAX_PAUSA_MS     = 2000;

    private CuentaBancaria cuenta;
    private String nombre;
    private Random random = new Random();

    public Cliente(CuentaBancaria cuenta, String nombre) {
        this.cuenta = cuenta;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        int numOperaciones = 1 + random.nextInt(MAX_OPERACIONES);

        for (int i = 0; i < numOperaciones; i++) {
            double cantidad = 1 + random.nextInt((int) MAX_CANTIDAD);

            // Aleatoriamente depositar o retirar
            boolean esDeposito = random.nextBoolean();

            if (esDeposito) {
                cuenta.depositar(cantidad);
                System.out.println(nombre + " DEPOSITÓ " + cantidad + "€  | Saldo: " + cuenta.getSaldo() + "€");
            } else {
                boolean exito = cuenta.retirar(cantidad);
                if (exito) {
                    System.out.println(nombre + " RETIRÓ   " + cantidad + "€  | Saldo: " + cuenta.getSaldo() + "€");
                } else {
                    System.out.println(nombre + " intentó retirar " + cantidad + "€ pero no hay saldo suficiente.");
                }
            }

            // Pausa aleatoria entre operaciones
            try {
                Thread.sleep(random.nextInt(MAX_PAUSA_MS));
            } catch (InterruptedException e) {
                System.out.println(nombre + " fue interrumpido.");
            }
        }

        System.out.println(nombre + " ha terminado sus operaciones.");
    }
}