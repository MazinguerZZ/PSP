package psp.hilos.Tarea3;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Empleado extends Thread{
    public static final int TIEMPO_ESPERA = 500;
    public final Semaphore ordenador, mesa;


    private String nombre;
    private int codEmpleado;

    public Empleado(Semaphore ordenador, Semaphore mesa, String nombre, int codEmpleado) {
        this.ordenador = ordenador;
        this.mesa = mesa;
        this.nombre = nombre;
        this.codEmpleado = codEmpleado;
    }

    @Override
    public void run(){

        Random random = new Random();

        int randomTime = (int) (Math.random() * 1000);

        try {

            sleep(TIEMPO_ESPERA);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
