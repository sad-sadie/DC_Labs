package com.my.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class Abiturient implements Serializable {

    @Serial
    private static final long serialVersionUID = 1234567L;

    int id;
    String firstName;
    String lastName;
    String middleName;
    String address;
    String phoneNumber;
    List<Integer> grades;
}
