package com.my.socket;

import lombok.SneakyThrows;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocketTask4 {
    private static ObjectOutputStream out = null;
    private static ObjectInputStream in = null;


    public ClientSocketTask4(String ip, int port) throws IOException {
        Socket sock = new Socket(ip, port);
        out = new ObjectOutputStream(sock.getOutputStream());
        in = new ObjectInputStream(sock.getInputStream());
    }

    private static Object sendQuery(int operation, Object object) throws IOException, ClassNotFoundException {
        out.writeInt(operation);
        out.writeObject(object);
        return in.readObject();
    }

    private Object getAbiturientsWithUnsatisfiedGrades() throws IOException, ClassNotFoundException {
        return sendQuery(0, null);
    }

    private Object getAbiturientsWithSumOfGradesMoreThanValue(int value) throws IOException, ClassNotFoundException {
        return sendQuery(1, value);
    }

    private Object getNBestAbiturients(int n) throws IOException, ClassNotFoundException {
        return sendQuery(2, n);
    }

    private Object getAbiturientsWithSemiPassGrades()  throws IOException, ClassNotFoundException {
        return sendQuery(3, null);
    }


    @SneakyThrows
    public static void main(String[] args) {
        ClientSocketTask4 client = new ClientSocketTask4("localhost", 12345);
        System.out.println("Socket client created");

        System.out.println(client.getAbiturientsWithUnsatisfiedGrades());
        System.out.println(client.getAbiturientsWithSumOfGradesMoreThanValue(38));
        System.out.println(client.getNBestAbiturients(2));
        System.out.println(client.getAbiturientsWithSemiPassGrades());
    }
}