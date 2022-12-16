package com.my.rmi;

import lombok.SneakyThrows;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AnotherClientRmiTask4 {

    @SneakyThrows
    public static void main(String[] args) {
        Registry registry = LocateRegistry.getRegistry(12345);
        RemoteController server = (RemoteController) registry
                .lookup("daoAbiturient");
        System.out.println("RMI client created");

        System.out.println(server.getAbiturientsWithUnsatisfiedGrades());
        Thread.sleep(10_000);
        System.out.println(server.getAbiturientsWithSumOfGradesMoreThanValue(38));
        System.out.println(server.getNBestAbiturients(2));
        System.out.println(server.getAbiturientsWithSemiPassGrades());
    }
}
