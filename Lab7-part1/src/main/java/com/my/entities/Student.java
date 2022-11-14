package com.my.entities;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class Student {
    private String id;
    private String firstName;
    private String lastName;
    private String groupId;
}
