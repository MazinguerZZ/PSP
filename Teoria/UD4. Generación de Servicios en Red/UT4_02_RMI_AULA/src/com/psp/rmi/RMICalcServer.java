package com.psp.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMICalcServer implements RMICalcInterface{

    @Override
    public int suma(int a, int b) throws RemoteException {
        return a+b;
    }

    @Override
    public int resta(int a, int b) throws RemoteException {
        return a-b;
    }

    @Override
    public int multip(int a, int b) throws RemoteException {
        return a*b;
    }

    @Override
    public int div(int a, int b) throws RemoteException {
        return a/b;
    }

    public static void main(String[] args) {

        Registry reg= null;

        try {
            reg= LocateRegistry.createRegistry(5555);

            RMICalcServer serverObject= new RMICalcServer();

            reg.rebind("Calculadora", (RMICalcInterface)UnicastRemoteObject.exportObject(serverObject, 0));

        } catch (RemoteException e) {
            System.out.println("ERROR no se ha podido crear el registro.");
            throw new RuntimeException(e);
        }

    }

}