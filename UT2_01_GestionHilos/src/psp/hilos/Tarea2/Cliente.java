package psp.hilos.Tarea2;

import java.util.Scanner;

public class Cliente extends CuentaBancaria implements Runnable{
    Scanner sc = new Scanner(System.in);

    @Override
    public void run(){
        System.out.println("Introduce un numero de clientes: ");
        int index = sc.nextInt();
        for (int i = 0; i < index; i++) {
            double random = Math.floor(Math.random() * 2) * 0;
            if (random == 0) {
                Thread.currentThread().setName("Cliente1");
                System.out.println(Thread.currentThread().getName());
                depositarDinero();
            }
            else {
                Thread.currentThread().setName("Cliente1");
                System.out.println(Thread.currentThread().getName());
                depositarDinero();            }
        }
    }
}
