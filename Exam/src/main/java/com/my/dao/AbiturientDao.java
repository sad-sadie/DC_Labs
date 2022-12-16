package com.my.dao;

import com.my.entity.Abiturient;

import java.util.Comparator;
import java.util.List;

public class AbiturientDao {

    private final List<Abiturient> abiturients;
    private static AbiturientDao instance;


    private AbiturientDao() {
        abiturients = List.of(
                Abiturient.builder()
                        .id(1)
                        .firstName("Vova")
                        .lastName("Kirilov")
                        .middleName("Anatoliyovich")
                        .address("Street 2, House 3")
                        .phoneNumber("0934235679")
                        .grades(List.of(8, 5, 9, 8, 10))
                        .build(),

                Abiturient.builder()
                        .id(2)
                        .firstName("Illia")
                        .lastName("Kuzkuz")
                        .middleName("Volodimirovich")
                        .address("Street 6, House 2")
                        .phoneNumber("0934236513")
                        .grades(List.of(7, 5, 6, 6, 7))
                        .build(),

                Abiturient.builder()
                        .id(3)
                        .firstName("Kirill")
                        .lastName("Schupschup")
                        .middleName("Romanovich")
                        .address("Street 3, House 7")
                        .phoneNumber("0934235636")
                        .grades(List.of(2, 5, 6, 5, 5))
                        .build()
        );
    }


    public static synchronized AbiturientDao getInstance() {
        if(instance == null) {
            instance = new AbiturientDao();
        }
        return instance;
    }


    public List<Abiturient> getAbiturientsWithUnsatisfiedGrades() {
        return abiturients.stream()
                .filter(abiturient -> abiturient.getGrades().stream()
                        .anyMatch(mark -> mark < 5))
                .toList();
    }

    public List<Abiturient> getAbiturientsWithSumOfGradesMoreThanValue(int value) {
        return abiturients.stream()
                .filter(abiturient -> abiturient.getGrades().stream()
                        .mapToInt(Integer::intValue)
                        .sum() > value)
                .toList();
    }

    public List<Abiturient> getNBestAbiturients(int n) {
        return abiturients.stream()
                .sorted(Comparator
                        .comparing(abiturient -> abiturient.getGrades().stream().
                        mapToInt(Integer::intValue)
                        .sum(), Comparator.reverseOrder())
                )
                .limit(n)
                .toList();
    }

    public List<Abiturient> getAbiturientsWithSemiPassGrades() {
        return abiturients.stream()
                .filter(abiturient -> abiturient.getGrades().stream()
                        .mapToInt(Integer::intValue)
                        .sum() / abiturient.getGrades().size() >= 5)
                .filter(abiturient -> abiturient.getGrades().stream()
                        .mapToInt(Integer::intValue)
                        .sum() / abiturient.getGrades().size() < 8)
                .toList();
    }
}
