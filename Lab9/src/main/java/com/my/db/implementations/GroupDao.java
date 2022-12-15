package com.my.db.implementations;

import com.my.db.GenericDao;
import com.my.entity.Group;
import com.my.mapper.GroupMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDao implements GenericDao<Group> {

    private final Connection connection;
    private final GroupMapper groupMapper;

    public GroupDao(Connection connection) {
        this.connection = connection;
        groupMapper = new GroupMapper();
    }

    @Override
    public void add(Group group) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO groups (name, course) VALUES(?, ?)");
            statement.setString(1, group.getName());
            statement.setInt(2, group.getCourse());
            statement.execute();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Group findById(int id) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM groups WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return groupMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Group> findAll() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM groups");
            ResultSet resultSet = statement.executeQuery();
            List<Group> groups = new ArrayList<>();
            while(resultSet.next()){
                groups.add(groupMapper.extractFromResultSet(resultSet));
            }
            return groups;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Group group) {
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


    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM groups WHERE id=?");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
