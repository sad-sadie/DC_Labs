package com.my;

import com.my.entity.Group;
import com.my.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBManager {

    private static final String URL
            = "jdbc:postgresql://localhost:5432/students-groups?user=postgres&password=123456";

    public static Connection connection;
    private static DBManager instance;

    private DBManager() {
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DBManager getInstance() {
        if(instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public void createGroup(Group group) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO groups (name, course) VALUES(?, ?)");
            statement.setString(1, group.getName());
            statement.setInt(2, group.getCourse());
            statement.execute();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void createStudent(Student student) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO students (first_name, last_name, group_id) VALUES(?, ?, ?)"
            );
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setInt(3, student.getGroupId());
            statement.execute();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void deleteGroup(Group group) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM groups WHERE id=?");
            statement.setInt(1, group.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteStudent(Student student) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM students WHERE id=?");
            statement.setInt(1, student.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateGroup(Group group) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE groups SET name=?, course=? WHERE id=?");
            statement.setString(1, group.getName());
            statement.setInt(2, group.getCourse());
            statement.setInt(3, group.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStudent(Student student) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE students SET first_name=?, last_name=?, group_id=? WHERE id=?");
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setInt(3, student.getGroupId());
            statement.setInt(4, student.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Group getGroupById(int id) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM groups WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return Group.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .course(resultSet.getInt("course"))
                        .build();
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    public Student getStudentById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM students WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return Student.builder()
                        .id(resultSet.getInt("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .groupId(resultSet.getInt("group_id"))
                        .build();
            }
        } catch (SQLException e){
           throw new RuntimeException(e);
        }
        return null;
    }

    public List<Group> getAllGroups() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM groups");
            ResultSet resultSet = statement.executeQuery();
            List<Group> groups = new ArrayList<>();
            while(resultSet.next()){
                groups.add(Group.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .course(resultSet.getInt("course"))
                        .build());
            }
            return groups;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Student> getStudentsByGroupId(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM students WHERE group_id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<Student> students = new ArrayList<>();
            while(resultSet.next()){
                students.add(Student.builder()
                        .id(resultSet.getInt("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .groupId(resultSet.getInt("group_id"))
                        .build());
            }
            return students;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        DBManager dbManager = DBManager.getInstance();
/*
            Group group1 = Group.builder()
                .name("group-1")
                .course(2)
                .build();
        Group group2 = Group.builder()
                .name("group-2")
                .course(5)
                .build();

        dbManager.createGroup(group1);
        dbManager.createGroup(group2);*/

        //System.out.println(dbManager.getAllGroups());
        //System.out.println(dbManager.getStudentsByGroupId(3));

        //dbManager.deleteStudent(dbManager.getStudentById(4));

       /* Student student1 = Student.builder()
                .firstName("Vova")
                .lastName("Kirilov")
                .groupId(3)
                .build();

        Student student2 = Student.builder()
                .firstName("Kyryl")
                .lastName("Shupshup")
                .groupId(3)
                .build();

        Student student3 = Student.builder()
                .firstName("Illia")
                .lastName("Kuzkuz")
                .groupId(2)
                .build();



        dbManager.createStudent(student1);
        dbManager.createStudent(student2);
        dbManager.createStudent(student3);*/

        /*Group group = dbManager.getGroupById(1);
        dbManager.deleteGroup(group);*/

        /* Group group = dbManager.getGroupById(2);
         group.setName("newName");
         group.setCourse(3);
        dbManager.updateGroup(group);*/

        /* Student student = dbManager.getStudentById(3);
         student.setFirstName("vova");
        dbManager.updateStudent(student);*/



    }

}
