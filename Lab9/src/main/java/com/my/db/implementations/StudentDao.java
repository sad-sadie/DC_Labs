package com.my.db.implementations;

import com.my.db.GenericDao;
import com.my.entity.Group;
import com.my.entity.Student;
import com.my.mapper.GroupMapper;
import com.my.mapper.StudentMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements GenericDao<Student> {

    private final Connection connection;
    private final StudentMapper studentMapper;

    public StudentDao(Connection connection) {
        this.connection = connection;
        studentMapper = new StudentMapper();
    }

    @Override
    public void add(Student student) {
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

    @Override
    public Student findById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM students WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return studentMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Student> findAll() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM students");
            ResultSet resultSet = statement.executeQuery();
            List<Student> students = new ArrayList<>();
            while(resultSet.next()){
                students.add(studentMapper.extractFromResultSet(resultSet));
            }
            return students;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Student student) {
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

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM students WHERE id=?");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> getStudentsByGroupName(String name) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM students s JOIN groups g on s.group_id = g.id WHERE g.name=?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            List<Student> students = new ArrayList<>();
            while(resultSet.next()){
                students.add(studentMapper.extractFromResultSet(resultSet));
            }
            return students;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
