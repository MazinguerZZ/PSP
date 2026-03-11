package com.psp.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMICalcClient {
    public static void main(String[] args) {
        RMICalcInterface calc= null;

        try {
            Registry registry= LocateRegistry.getRegistry("localhost", 5555);
            calc= (RMICalcInterface) registry.lookup("Calculadora");

            if(calc != null) {
                int a= 8;
                int b= 4;
                System.out.println("Suma: " + calc.suma(a, b));
                System.out.println("Resta: " + calc.resta(a, b));
                System.out.println("Multpiclicación: " + calc.multip(a, b));
                System.out.println("División: " + calc.div(a, b));
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        }
    }
}
