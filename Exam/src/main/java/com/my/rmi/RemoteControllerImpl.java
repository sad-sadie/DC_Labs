package com.my.rmi;


import com.my.dao.AbiturientDao;
import com.my.entity.Abiturient;
import lombok.Synchronized;

import java.rmi.RemoteException;
import java.util.List;

public class RemoteControllerImpl implements RemoteController {

    private final AbiturientDao abiturientDao;

    public RemoteControllerImpl() {
        this.abiturientDao = AbiturientDao.getInstance();
    }


    @Override
    @Synchronized("abiturientDao")
    public List<Abiturient> getAbiturientsWithUnsatisfiedGrades() throws RemoteException {
        return abiturientDao.getAbiturientsWithUnsatisfiedGrades();
    }

    @Override
    @Synchronized("abiturientDao")
    public List<Abiturient> getAbiturientsWithSumOfGradesMoreThanValue(int value) throws RemoteException {
        return abiturientDao.getAbiturientsWithSumOfGradesMoreThanValue(value);
    }

    @Override
    @Synchronized("abiturientDao")
    public List<Abiturient> getNBestAbiturients(int n) throws RemoteException {
        return abiturientDao.getNBestAbiturients(n);
    }

    @Override
    @Synchronized("abiturientDao")
    public List<Abiturient> getAbiturientsWithSemiPassGrades() throws RemoteException {
        return abiturientDao.getAbiturientsWithSemiPassGrades();
    }
}
