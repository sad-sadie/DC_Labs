package com.my.entities;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Group {
    private String id;
    private String name;
    private int course;
    List<Student> students;
}
