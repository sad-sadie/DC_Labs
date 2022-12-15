package com.my.mapper;

import com.my.entity.Group;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupMapper implements Mapper<Group> {
    @Override
    public Group extractFromResultSet(ResultSet rs) throws SQLException {
        return Group.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .course(rs.getInt("course"))
                .build();
    }
}
