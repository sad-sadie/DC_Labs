package com.my.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerRmiTask4 {

    public static void main(String[] args) throws RemoteException {
        RemoteController server = new RemoteControllerImpl();
        RemoteController stub = (RemoteController) UnicastRemoteObject
                .exportObject(server, 12345);
        Registry registry = LocateRegistry.createRegistry(12345);
        registry.rebind("daoAbiturient", stub);
        System.out.println("RMI server started!");
    }
}
