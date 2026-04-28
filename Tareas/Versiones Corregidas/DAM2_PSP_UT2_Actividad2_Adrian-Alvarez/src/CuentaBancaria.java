public class CuentaBancaria {

    // Saldo inicial de la cuenta
    private static final double SALDO_INICIAL = 1000.0;

    private double saldo;

    public CuentaBancaria() {
        this.saldo = SALDO_INICIAL;
    }

    public synchronized double getSaldo() {
        return saldo;
    }

    public synchronized boolean retirar(double cantidad) {
        if (cantidad > saldo) {
            return false;
        }
        saldo -= cantidad;
        return true;
    }

    public synchronized void depositar(double cantidad) {
        saldo += cantidad;
    }
}