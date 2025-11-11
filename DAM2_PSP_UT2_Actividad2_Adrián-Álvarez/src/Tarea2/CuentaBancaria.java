package Tarea2;

public class CuentaBancaria {
    // Constantes con valores
    public static final float SALDO_INICIAL = 1000.0f;
    public static final float CANTIDAD_MAXIMA_OPERACION = 500.0f;
    public static final int MAX_OPERACIONES_POR_CLIENTE = 10;
    public static final int TIEMPO_MAXIMO_PAUSA = 500;

    private float saldo;
    private float totalDepositos;
    private float totalRetiros;

    public CuentaBancaria() {
        this.saldo = SALDO_INICIAL;
        this.totalDepositos = 0;
        this.totalRetiros = 0;
    }

    public synchronized float getSaldoActual() {
        return saldo;
    }

    public synchronized float getTotalDepositos() {
        return totalDepositos;
    }

    public synchronized float getTotalRetiros() {
        return totalRetiros;
    }

    public synchronized void retirarDinero(float cantidad, String nombreCliente) {
        if (cantidad <= saldo) {
            saldo -= cantidad;
            totalRetiros += cantidad;
            System.out.printf("%s ha retirado %.2f€\n", nombreCliente, cantidad);
        } else {
            System.out.printf("%s - ERROR: No puede retirar %.2f€ (Saldo insuficiente)\n", nombreCliente, cantidad);
        }
    }

    public synchronized void depositarDinero(float cantidad, String nombreCliente) {
        saldo += cantidad;
        totalDepositos += cantidad;
        System.out.printf("%s ha depositado %.2f€\n", nombreCliente, cantidad);
    }
}