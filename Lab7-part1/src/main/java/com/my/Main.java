package com.my;

import com.my.entities.Group;
import com.my.entities.Student;
import com.my.parsers.DomParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    private final static String PATH = "src/main/resources/university.xml";
    private final static String OUTPUT_PATH = "src/main/resources/output.xml";

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        University university = DomParser.parse(PATH);
        university.printAllGroupsAndStudents();

        Group group = Group.builder()
                .name("newGroup")
                .course(3)
                .students(new ArrayList<>())
                .build();
        university.createGroup(group);
        university.printAllGroupsAndStudents();

        Student student1 = Student.builder()
                .firstName("Vania")
                .lastName("Gavrilov")
                .groupId(group.getId())
                .build();
        university.createStudent(student1);
        Student student2 = Student.builder()
                .firstName("Gosha")
                .lastName("Kuzmin")
                .groupId(group.getId())
                .build();
        university.createStudent(student2);
        Student student3 = Student.builder()
                .firstName("SomeName")
                .lastName("SomeOtherName")
                .groupId(group.getId())
                .build();
        university.createStudent(student3);
        university.printAllGroupsAndStudents();

        System.out.println(university.getGroupById("2"));
        System.out.println(university.getStudentById("2"));
        System.out.println("≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈");

        student2.setFirstName("NewFirstName");
        student2.setLastName("NewLastName");
        university.updateStudent(student2);
        university.printAllGroupsAndStudents();


        group.setCourse(5);
        group.setName("newGroupName");
        university.updateGroup(group);
        university.printAllGroupsAndStudents();

        System.out.println(university.getStudents());
        System.out.println(university.getStudentsByGroupId(group.getId()));
        System.out.println("≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈");

        university.deleteStudent(student3);
        university.printAllGroupsAndStudents();
        university.deleteGroup(group);
        university.printAllGroupsAndStudents();
        DomParser.write(university, OUTPUT_PATH);
    }
}