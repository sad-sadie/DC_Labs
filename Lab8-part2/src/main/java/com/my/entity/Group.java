package com.my.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class Group implements Serializable {

    @Serial
    private static final long serialVersionUID = 1234567L;

    private Integer id;
    private String name;
    private Integer course;
}
