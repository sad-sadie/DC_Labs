package com.my.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Group {
    private int id;
    private String name;
    private int course;
}
