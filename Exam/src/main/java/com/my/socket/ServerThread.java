package com.my.socket;

import com.my.dao.AbiturientDao;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private final Socket socket;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ServerThread(Socket clientSocket) {
        this.socket = clientSocket;

    }

    private boolean processQuery() {
        AbiturientDao abiturientDao = AbiturientDao.getInstance();
        try {
            int oper = in.readInt();
            Object object = in.readObject();
            System.out.println(object);
            Object result = null;
            switch (oper) {
                case 0 -> result = abiturientDao.getAbiturientsWithUnsatisfiedGrades();
                case 1 -> {
                    Integer value = (Integer) object;
                    result = abiturientDao.getAbiturientsWithSumOfGradesMoreThanValue(value);
                }
                case 2 -> {
                    Integer n = (Integer) object;
                    result = abiturientDao.getNBestAbiturients(n);
                }
                case 3 -> result = abiturientDao.getAbiturientsWithSemiPassGrades();
            }

            out.writeObject(result);
            return true;
        } catch (IOException e) {
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            while (processQuery());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
