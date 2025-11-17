package psp.hilos.Tarea2;

public class CuentaBancaria {

    final float salarioActual = 0;
    static float salarioTotal = 0;

    public void devolverSaldoActual() {
        this.salarioTotal = salarioActual;
    }

    public static void retirarDinero() {
        salarioTotal -= (float) Math.random()*1;
        System.out.println("Se ha retirado de la cuenta: " + salarioTotal);
    }

    public static void depositarDinero() {
        salarioTotal += (float) Math.random()*1;
        System.out.println("Se ha depositado en la cuenta: " + salarioTotal);

    }

}
