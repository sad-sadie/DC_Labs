package com.my;

import com.my.entity.Group;
import com.my.entity.Student;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    // главный метод
    public static void main(String[] args) {

        try {
            Server srv = new Server();
            srv.start(12345);
        } catch (IOException e) {
            System.out.println("Виникла помилка");
        }
    }
    public void start(int port) throws IOException {
        ServerSocket server = new ServerSocket(port);
        while (true) {
            Socket sock = server.accept();
            in = new ObjectInputStream(sock.getInputStream());
            out = new ObjectOutputStream(sock.getOutputStream());
            while (processQuery());
        }
    }
    private boolean processQuery() {
        DBManager dbManager = DBManager.getInstance();
        try {
            int oper = in.readInt();
            Object object = in.readObject();
            System.out.println(object);
            Object result = null;
            switch (oper) {
                case 0 -> {
                    Group group = (Group) object;
                    result = dbManager.createGroup(group);
                }
                case 1 -> {
                    Student student = (Student) object;
                    result = dbManager.createStudent(student);
                }
                case 2 -> {
                    Group group = (Group) object;
                    result = dbManager.deleteGroup(group);
                }
                case 3 -> {
                    Student student = (Student) object;
                    result = dbManager.deleteStudent(student);
                }
                case 4 -> {
                    Group group = (Group) object;
                    result = dbManager.updateGroup(group);
                }
                case 5 -> {
                    Student student = (Student) object;
                    System.out.println(student);
                    result = dbManager.updateStudent(student);
                }
                case 6 -> {
                    Integer id = (Integer) object;
                    result = dbManager.getGroupById(id);
                }
                case 7 -> {
                    Integer id = (Integer) object;
                    result = dbManager.getStudentById(id);
                }
                case 8 -> result = dbManager.getAllGroups();
                case 9 -> {
                    Integer id = (Integer) object;
                    result = dbManager.getStudentsByGroupId(id);
                }
                case 10 -> result = dbManager.getAllStudents();
                case 11 -> {
                    String name = (String) object;
                    result = dbManager.getGroupByName(name);
                }
                case 12 -> {
                    String lastName = (String) object;
                    result = dbManager.getStudentByLastName(lastName);
                }

            }

            out.writeObject(result);
            return true;
        } catch (IOException e) {
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}