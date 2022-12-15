package com.my.db;

import org.apache.commons.dbcp.BasicDataSource;
import javax.sql.DataSource;

public class ConnectionPool {

    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPool.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setDriverClassName("org.postgresql.Driver");
                    ds.setUrl("jdbc:postgresql://localhost:5432/university");
                    ds.setUsername("postgres");
                    ds.setPassword("123456");
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }
}
