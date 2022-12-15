package com.my.db.implementations;


import com.my.db.ConnectionPool;
import com.my.db.DaoFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactoryImpl extends DaoFactory {

    private final DataSource dataSource = ConnectionPool.getDataSource();

    private StudentDao studentDao;

    private GroupDao groupDao;

    @Override
    public StudentDao createStudentDao() {
        if (studentDao == null) {
            studentDao = new StudentDao(getConnection());
        }
        return studentDao;
    }

    @Override
    public GroupDao createGroupDao() {
        if (groupDao == null) {
            groupDao = new GroupDao(getConnection());
        }
        return groupDao;
    }
    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
