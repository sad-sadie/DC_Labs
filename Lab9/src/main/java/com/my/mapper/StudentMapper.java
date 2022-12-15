package com.my.mapper;

import com.my.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements Mapper<Student> {

    @Override
    public Student extractFromResultSet(ResultSet rs) throws SQLException {
        return Student.builder()
                .id(rs.getInt("id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .groupId(rs.getInt("group_id"))
                .build();
    }
}
