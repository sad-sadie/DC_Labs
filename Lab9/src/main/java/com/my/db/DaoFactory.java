package com.my.db;

import com.my.db.implementations.DaoFactoryImpl;
import com.my.db.implementations.GroupDao;
import com.my.db.implementations.StudentDao;
import com.my.entity.Student;

public abstract class DaoFactory {

    private static volatile DaoFactory daoFactory;

    public abstract GroupDao createGroupDao();

    public abstract StudentDao createStudentDao();

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if(daoFactory == null) {
                    daoFactory = new DaoFactoryImpl();
                }
            }
        }
        return daoFactory;
    }
}
