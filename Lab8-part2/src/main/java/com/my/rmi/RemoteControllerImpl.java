package com.my.rmi;

import com.my.DBManager;
import com.my.entity.Group;
import com.my.entity.Student;
import lombok.SneakyThrows;
import lombok.Synchronized;

import java.rmi.RemoteException;
import java.util.List;

public class RemoteControllerImpl implements RemoteController {

    private final DBManager dbManager;
    private int numberOfClients;

    public RemoteControllerImpl() {
        this.dbManager = DBManager.getInstance();
    }

    @Override
    public int getNumberOfClients() throws RemoteException {
        return numberOfClients;
    }

    @Override
    public void addClient() throws RemoteException {
        ++numberOfClients;
    }

    @Override
    @Synchronized("dbManager")
    public void createGroup(Group group) throws RemoteException {
        dbManager.createGroup(group);
    }

    @Override
    @Synchronized("dbManager")
    public void createStudent(Student student) throws RemoteException {
        dbManager.createStudent(student);
    }

    @Override
    @Synchronized("dbManager")
    public void deleteGroup(Group group) throws RemoteException{
        dbManager.deleteGroup(group);
    }

    @Override
    @Synchronized("dbManager")
    public void deleteStudent(Student student) throws RemoteException {
        dbManager.deleteStudent(student);
    }

    @Override
    @Synchronized("dbManager")
    public void updateGroup(Group group) throws RemoteException {
        dbManager.updateGroup(group);
    }

    @Override
    @Synchronized("dbManager")
    public void updateStudent(Student student) throws RemoteException {
        dbManager.updateStudent(student);
    }

    @Override
    @Synchronized("dbManager")
    public Group getGroupById(int id) throws RemoteException{
        return dbManager.getGroupById(id);
    }

    @Override
    @Synchronized("dbManager")
    public Group getGroupByName(String name) throws RemoteException {
        return dbManager.getGroupByName(name);
    }

    @Override
    @Synchronized("dbManager")
    public Student getStudentById(int id) throws RemoteException{
        return dbManager.getStudentById(id);
    }

    @Override
    @Synchronized("dbManager")
    public Student getStudentByLastName(String lastName) throws RemoteException {
        return dbManager.getStudentByLastName(lastName);
    }

    @Override
    @Synchronized("dbManager")
    public List<Group> getAllGroups() throws RemoteException {
        return dbManager.getAllGroups();
    }

    @Override
    @Synchronized("dbManager")
    public List<Student> getStudentsByGroupId(int id) throws RemoteException {
        return dbManager.getStudentsByGroupId(id);
    }

    @Override
    @Synchronized("dbManager")
    public void printAllGroupsAndStudents() throws RemoteException {
        dbManager.printAllGroupsAndStudents();
    }

    @Override
    @Synchronized("dbManager")
    public List<Student> getAllStudents() throws RemoteException {
        return dbManager.getAllStudents();
    }
}
