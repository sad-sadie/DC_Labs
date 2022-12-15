package com.my;

import com.my.entity.Group;
import com.my.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBManager {

    private static final String URL
            = "jdbc:postgresql://localhost:5432/students-groups?user=postgres&password=123456";

    private static DBManager instance;
    public static Connection connection;

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

    public Group getGroupByName(String name) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM groups WHERE name = ?");
            preparedStatement.setString(1, name);
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

    public Student getStudentByLastName(String lastName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM students WHERE last_name = ?");
            preparedStatement.setString(1, lastName);
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

    public void printAllGroupsAndStudents() {
        DBManager dbManager = DBManager.getInstance();
        List<Group> groups = dbManager.getAllGroups();
        groups.forEach(group -> {
                    System.out.println(group);
                    dbManager.getStudentsByGroupId(group.getId()).forEach(System.out::println);
                }
        );
        System.out.println("≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈");
    }

    public List<Student> getAllStudents() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM students");
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
}
