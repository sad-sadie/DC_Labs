package com.my.server;

import com.my.rmi.RemoteController;
import com.my.rmi.RemoteControllerImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public static void main(String[] args) throws RemoteException {
        RemoteController server = new RemoteControllerImpl();
        RemoteController stub = (RemoteController) UnicastRemoteObject
                .exportObject(server, 12345);
        Registry registry = LocateRegistry.createRegistry(12345);
        registry.rebind("DBController", stub);
        System.out.println("Server started!");
    }
}
