package com.my.rmi;


import com.my.entity.Abiturient;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteController extends Remote {

    List<Abiturient> getAbiturientsWithUnsatisfiedGrades() throws RemoteException;

    List<Abiturient> getAbiturientsWithSumOfGradesMoreThanValue(int value) throws RemoteException;

    List<Abiturient> getNBestAbiturients(int n) throws RemoteException;

    List<Abiturient> getAbiturientsWithSemiPassGrades() throws RemoteException;

}
