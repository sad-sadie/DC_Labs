package com.my.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketTask4 {

    public static void main(String[] args) {

        try {
            ServerSocketTask4 srv = new ServerSocketTask4();
            srv.start(12345);
        } catch (IOException e) {
            System.out.println("Виникла помилка");
        }
    }

    public void start(int port) throws IOException {
        ServerSocket server = new ServerSocket(port);
        System.out.println("Socket server started");
        while (true) {
            Socket sock = server.accept();
            new ServerThread(sock).start();
        }
    }
}