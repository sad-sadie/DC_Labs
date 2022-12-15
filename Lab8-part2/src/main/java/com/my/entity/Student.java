package com.my.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class Student implements Serializable {

    @Serial
    private static final long serialVersionUID = 1234568L;

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer groupId;
}
