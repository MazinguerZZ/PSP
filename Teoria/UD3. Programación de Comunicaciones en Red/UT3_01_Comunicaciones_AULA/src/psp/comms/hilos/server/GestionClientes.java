package psp.comms.hilos.server;

import psp.comms.hilos.model.Ejemplo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class GestionClientes extends Thread {

    private int numCliente;
    private Socket cliente;

    public GestionClientes (Socket cliente, int numCliente) {
        this.numCliente= numCliente;
        this.cliente= cliente;
    }

    @Override
    public void run() {

        ObjectInputStream bufferObjetosEntrada= null;
        ObjectOutputStream bufferObjetosSalida= null;

        System.out.println("Hilo de atención al cliente: " + numCliente);
        try {
            bufferObjetosEntrada= new ObjectInputStream(cliente.getInputStream());
            Ejemplo datosEntrada= (Ejemplo) bufferObjetosEntrada.readObject();
            datosEntrada.mostrar();
            System.out.println("Enviado al cliente " + numCliente + " el objeto " + datosEntrada.toString());

            Ejemplo datosSalida= new Ejemplo("Objeto del servidor", numCliente);
            bufferObjetosSalida= new ObjectOutputStream(cliente.getOutputStream());
            bufferObjetosSalida.writeObject(datosSalida);
            bufferObjetosSalida.flush();
            System.out.println("Enviado al cliente " + numCliente + " el objeto " + datosSalida.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                bufferObjetosEntrada.close();
                bufferObjetosSalida.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
