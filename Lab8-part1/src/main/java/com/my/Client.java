package com.my;

import com.my.entity.Group;
import com.my.entity.Student;

import java.io.*;
import java.net.Socket;
public class Client {
    private final Socket sock;
    private static ObjectOutputStream out = null;
    private static ObjectInputStream in = null;


    public Client(String ip, int port) throws IOException {
        sock = new Socket(ip, port);
        out = new ObjectOutputStream(sock.getOutputStream());
        in = new ObjectInputStream(sock.getInputStream());

    }

    private static Object sendQuery(int operation, Object object) throws IOException, ClassNotFoundException {
        out.writeInt(operation);
        out.writeObject(object);
        return in.readObject();
    }

    private Object createGroup(Group group) throws IOException, ClassNotFoundException {
        return sendQuery(0, group);
    }

    private Object createStudent(Student student) throws IOException, ClassNotFoundException {
        return sendQuery(1, student);
    }

    private Object deleteGroup(Group group) throws IOException, ClassNotFoundException {
        return sendQuery(2, group);
    }

    private Object deleteStudent(Student student) throws IOException, ClassNotFoundException {
        return sendQuery(3, student);
    }

    private Object updateGroup(Group group) throws IOException, ClassNotFoundException {
        return sendQuery(4, group);
    }

    private Object updateStudent(Student student) throws IOException, ClassNotFoundException {
        return sendQuery(5, student);
    }

    private Object getGroupById(Integer id) throws IOException, ClassNotFoundException {
        return sendQuery(6, id);
    }

    private Object getStudentById(Integer id) throws IOException, ClassNotFoundException {
        return sendQuery(7, id);
    }

    private Object getAllGroups() throws IOException, ClassNotFoundException {
        return sendQuery(8, null);
    }

    private Object getStudentsByGroupId(Integer id) throws IOException, ClassNotFoundException {
        return sendQuery(9, id);
    }

    private Object getAllStudents() throws IOException, ClassNotFoundException {
        return sendQuery(10, null);
    }

    private Object getGroupByName(String name) throws IOException, ClassNotFoundException {
        return sendQuery(11, name);
    }

    private Object getStudentByLastName(String lastName) throws IOException, ClassNotFoundException {
        return sendQuery(12, lastName);
    }

    public void disconnect() throws IOException {
        sock.close();
    }

    public static void main(String[] args) {
        try {
            Client client = new Client("localhost", 12345);
            System.out.println(client.getAllGroups());
            System.out.println(client.getAllStudents());
            System.out.println("≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈");


            Group group = Group.builder()
                    .name("groupppp")
                    .course(3)
                    .build();
            System.out.println(client.createGroup(group));
            System.out.println(client.getAllGroups());
            System.out.println(client.getAllStudents());
            System.out.println("≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈");

            int groupId = ((Group) (client.getGroupByName("groupppp"))).getId();

            Student student1 = Student.builder()
                    .firstName("Vania")
                    .lastName("Gavrilov")
                    .groupId(groupId)
                    .build();
            System.out.println(client.createStudent(student1));
            Student student2 = Student.builder()
                    .firstName("Gosha")
                    .lastName("Kuzmin")
                    .groupId(groupId)
                    .build();
            System.out.println(client.createStudent(student2));
            Student student3 = Student.builder()
                    .firstName("SomeName")
                    .lastName("SomeOtherName")
                    .groupId(groupId)
                    .build();
            System.out.println(client.createStudent(student3));
            System.out.println(client.getAllGroups());
            System.out.println(client.getAllStudents());
            System.out.println("≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈");

            System.out.println(client.getGroupById(2));
            System.out.println(client.getStudentById(3));
            System.out.println("≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈");

            Student student4 = Student.builder()
                    .firstName("NewFirstName")
                    .lastName("NewLastName")
                    .groupId(groupId)
                    .build();
            student4.setId(((Student)(client.getStudentByLastName("Kuzmin"))).getId());
            System.out.println(client.updateStudent(student4));
            System.out.println(client.getAllGroups());
            System.out.println(client.getAllStudents());
            System.out.println("≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈");


            Group newGroup = Group.builder()
                    .name("newGroupName")
                    .course(5)
                    .build();
            newGroup.setId(((Group) (client.getGroupByName("groupppp"))).getId());
            System.out.println(client.updateGroup(newGroup));
            System.out.println(client.getAllGroups());
            System.out.println(client.getAllStudents());
            System.out.println("≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈");

            System.out.println(client.getAllGroups());
            System.out.println(client.getStudentsByGroupId(newGroup.getId()));
            System.out.println("≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈");

            System.out.println(client.deleteStudent(student4));
            System.out.println(client.getAllGroups());
            System.out.println(client.getAllStudents());
            System.out.println("≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈");

            System.out.println(client.deleteGroup(newGroup));
            System.out.println(client.getAllGroups());
            System.out.println(client.getAllStudents());
            System.out.println("≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈");

            client.disconnect();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Виникла помилка");
        }
    }
}