package com.my.parsers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.my.University;
import com.my.entities.Group;
import com.my.entities.Student;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DomParser {

    public static class SimpleErrorHandler implements ErrorHandler {
        public void warning(SAXParseException e) {
            System.out.println("Line " + e.getLineNumber() + ":");
            System.out.println(e.getMessage());
        }
        public void error(SAXParseException e) {
            System.out.println("Line " + e.getLineNumber() + ":");
            System.out.println(e.getMessage());
        }
        public void fatalError(SAXParseException e) {
            System.out.println("Line " + e.getLineNumber() + ":");
            System.out.println(e.getMessage());
        }
    }

    public static University parse(String path) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setErrorHandler(new SimpleErrorHandler());
        Document doc = builder.parse(new File(path));
        doc.getDocumentElement().normalize();

        University university = University.getInstance();
        NodeList nodes = doc.getElementsByTagName("group");

        IntStream.range(0, nodes.getLength()).mapToObj(i -> (Element) nodes.item(i)).forEach(groupNode -> {
            Group group = Group.builder()
                    .id(groupNode.getAttribute("id"))
                    .name(groupNode.getAttribute("name"))
                    .course(Integer.parseInt(groupNode.getAttribute("course")))
                    .students(new ArrayList<>())
                    .build();
            university.createGroup(group);
            IntStream.range(0, groupNode.getElementsByTagName("student").getLength()).mapToObj(j -> (Element) groupNode.getElementsByTagName("student").item(j)).map(studentNode -> Student.builder()
                    .id(studentNode.getAttribute("id"))
                    .groupId(groupNode.getAttribute("id"))
                    .firstName(studentNode.getAttribute("firstName"))
                    .lastName(studentNode.getAttribute("lastName"))
                    .build()).forEach(university::createStudent);
        });
        return university;
    }

    public static void write(University university, String path) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element root = doc.createElement("university");
        doc.appendChild(root);

        List<Group> groups = university.getGroups();
        groups.forEach(group -> {
            Element groupNode = doc.createElement("group");
            groupNode.setAttribute("id", group.getId());
            groupNode.setAttribute("name", group.getName());
            groupNode.setAttribute("course", String.valueOf(group.getCourse()));
            root.appendChild(groupNode);
            for (Student student : group.getStudents()) {
                Element studentName = doc.createElement("student");
                studentName.setAttribute("id", student.getId());
                studentName.setAttribute("firstName", student.getFirstName());
                studentName.setAttribute("lastName", student.getLastName());
                groupNode.appendChild(studentName);
            }
        });
        Source domSource = new DOMSource(doc);
        Result fileResult = new StreamResult(new File(path));
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "university.xsd");
        transformer.transform(domSource, fileResult);
    }
}