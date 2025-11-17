package psp.hilos.Tarea2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main extends CuentaBancaria{
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.println("Introduce un numero de clientes: ");
        int index = sc.nextInt();
        List<Cliente> clientes = new ArrayList<>();

        for (int i = 0; i < index; i++) {
            double random = Math.floor(Math.random()*1)*0;
            if (random == 0) {
                depositarDinero();
            }
            else {
                retirarDinero();
            }
            clientes.add(new Cliente());

        }

        for (Cliente cliente2 : clientes) {
            cliente2.run();
        }
    }
}
