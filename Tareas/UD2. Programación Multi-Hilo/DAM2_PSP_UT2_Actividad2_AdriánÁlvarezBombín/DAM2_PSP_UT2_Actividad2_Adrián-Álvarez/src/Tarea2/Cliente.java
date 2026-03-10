package Tarea2;

public class Cliente implements Runnable {
    private CuentaBancaria cuenta;
    private String nombre;

    public Cliente(CuentaBancaria cuenta, String nombre) {
        this.cuenta = cuenta;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        int numOperaciones = (int) (Math.random() * CuentaBancaria.MAX_OPERACIONES_POR_CLIENTE) + 1;

        for (int i = 0; i < numOperaciones; i++) {
            int tipoOperacion = (int) (Math.random() * 2);
            float cantidad = (float) (Math.random() * CuentaBancaria.CANTIDAD_MAXIMA_OPERACION);

            // Redondear a 2 decimales
            cantidad = Math.round(cantidad * 100.0f) / 100.0f;

            if (tipoOperacion == 0) {
                cuenta.retirarDinero(cantidad, nombre);
            } else {
                cuenta.depositarDinero(cantidad, nombre);
            }

            try {
                Thread.sleep((long) (Math.random() * CuentaBancaria.TIEMPO_MAXIMO_PAUSA));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}