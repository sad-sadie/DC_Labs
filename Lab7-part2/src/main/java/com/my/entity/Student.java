package com.my.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private int groupId;
}
