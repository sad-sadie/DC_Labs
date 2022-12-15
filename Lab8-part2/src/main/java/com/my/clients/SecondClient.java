package com.my.clients;

import com.my.entity.Group;
import com.my.rmi.RemoteController;
import lombok.SneakyThrows;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SecondClient {
    @SneakyThrows
    public static void main(String[] args){
        Registry registry = LocateRegistry.getRegistry(12345);
        RemoteController server = (RemoteController) registry
                .lookup("DBController");
        server.addClient();

        System.out.println("RMI object found");
        while (true)
            if(server.getNumberOfClients() == 2 ) {
                int nameSuffix = 0;
                for (int i = 0; i < 3; i++) {
                    Group group = Group.builder()
                            .name("grouppy-" + nameSuffix++)
                            .course(1)
                            .build();
                    server.createGroup(group);
                }
                break;
            }
    }
}