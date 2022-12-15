package com.my;

import com.my.entities.Group;
import com.my.entities.Student;
import lombok.Getter;


import java.util.*;

@Getter
public class University {

    public static University university;

    private final List<Group> groups = new ArrayList<>();
    private final List<Student> students = new ArrayList<>();

    private University() {
    }

    public static synchronized University getInstance() {
        if(university == null) {
            university = new University();
        }
        return university;
    }

    public void createGroup(Group group) {
        if (group.getId() == null) {
            group.setId(UUID.randomUUID().toString());
        }
        groups.add(group);
    }

    public void createStudent(Student student) {
        if (student.getId() == null) {
            student.setId(UUID.randomUUID().toString());
        }
        students.add(student);
        university.getGroupById(student.getGroupId()).getStudents().add(student);
    }

    public Group getGroupById(String id) {
        return groups.stream().filter(group -> group.getId().equals(id)).findFirst().orElse(null);
    }

    public Student getStudentById(String id) {
        return students.stream().filter(student -> student.getId().equals(id)).findFirst().orElse(null);
    }

    public void deleteGroup(Group group) {
        group.getStudents().forEach(students::remove);
        groups.remove(group);
    }

    public void deleteStudent(Student student) {
        university.getGroupById(student.getGroupId()).getStudents().remove(student);
        students.remove(student);
    }

    public void updateGroup(Group newGroup) {
         Group group = university.getGroupById(newGroup.getId());
         group.setName(newGroup.getName());
         group.setCourse(newGroup.getCourse());
    }

    public void updateStudent(Student newStudent) {
        Student student = university.getStudentById(newStudent.getId());
        student.setFirstName(newStudent.getFirstName());
        student.setLastName(newStudent.getLastName());
    }

    public List<Student> getStudentsByGroupId(String id) {
        return new ArrayList<>(university.getGroupById(id).getStudents());
    }

    public void printAllGroupsAndStudents(){
        groups.forEach(System.out::println);
        System.out.println("≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈");
    }
}