package com.my;

import com.my.entity.Group;
import com.my.entity.Student;

public class Main {
    public static void main(String[] args) {
        DBManager dbManager = DBManager.getInstance();
        dbManager.printAllGroupsAndStudents();

        Group group = Group.builder()
                .name("groupppp")
                .course(3)
                .build();
        dbManager.createGroup(group);
        dbManager.printAllGroupsAndStudents();

        int groupId = (dbManager.getGroupByName("groupppp").getId());

        Student student1 = Student.builder()
                .firstName("Vania")
                .lastName("Gavrilov")
                .groupId(groupId)
                .build();
        dbManager.createStudent(student1);
        Student student2 = Student.builder()
                .firstName("Gosha")
                .lastName("Kuzmin")
                .groupId(groupId)
                .build();
        dbManager.createStudent(student2);
        Student student3 = Student.builder()
                .firstName("SomeName")
                .lastName("SomeOtherName")
                .groupId(groupId)
                .build();
        dbManager.createStudent(student3);
        dbManager.printAllGroupsAndStudents();

        System.out.println(dbManager.getGroupById(2));
        System.out.println(dbManager.getStudentById(3));
        System.out.println("≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈");

        student2.setFirstName("NewFirstName");
        student2.setLastName("NewLastName");
        student2.setId(dbManager.getStudentByLastName("Kuzmin").getId());
        dbManager.updateStudent(student2);
        dbManager.printAllGroupsAndStudents();


        group.setCourse(5);
        group.setName("newGroupName");
        group.setId(groupId);
        dbManager.updateGroup(group);
        dbManager.printAllGroupsAndStudents();

        System.out.println(dbManager.getAllGroups());
        System.out.println(dbManager.getStudentsByGroupId(group.getId()));
        System.out.println("≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈");

        dbManager.deleteStudent(student2);
        dbManager.printAllGroupsAndStudents();
        dbManager.deleteGroup(group);
        dbManager.printAllGroupsAndStudents();
    }
}
