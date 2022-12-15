package com.my.rmi;

import com.my.entity.Group;
import com.my.entity.Student;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteController extends Remote {

    int getNumberOfClients() throws RemoteException;

    void addClient() throws RemoteException;

    void createGroup(Group group) throws RemoteException;

    void createStudent(Student student) throws RemoteException;

    void deleteGroup(Group group) throws RemoteException;

    void deleteStudent(Student student) throws RemoteException;

    void updateGroup(Group group) throws RemoteException;

    void updateStudent(Student student) throws RemoteException;

    Group getGroupById(int id) throws RemoteException;

    Group getGroupByName(String name) throws RemoteException;

    Student getStudentById(int id) throws RemoteException;

    Student getStudentByLastName(String lastName) throws RemoteException;

    List<Group> getAllGroups() throws RemoteException;

    List<Student> getStudentsByGroupId(int id) throws RemoteException;

    void printAllGroupsAndStudents() throws RemoteException;

    List<Student> getAllStudents() throws RemoteException;
}
