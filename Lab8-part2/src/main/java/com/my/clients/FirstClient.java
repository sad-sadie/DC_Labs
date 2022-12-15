package com.my.clients;

import com.my.rmi.RemoteController;
import lombok.SneakyThrows;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FirstClient {
    @SneakyThrows
    public static void main(String[] args){
        Registry registry = LocateRegistry.getRegistry(12345);
        RemoteController server = (RemoteController) registry
                .lookup("DBController");
        server.addClient();
        System.out.println("RMI object found");
        while (true) {
            if(server.getNumberOfClients() == 2 ){
                System.out.println(server.getAllGroups());
                Thread.sleep(100);
                System.out.println(server.getAllStudents());
                break;
            }
        }
    }
}