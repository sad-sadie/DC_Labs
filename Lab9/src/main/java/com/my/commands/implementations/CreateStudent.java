package com.my.commands.implementations;

import com.my.commands.Command;
import com.my.db.implementations.GroupDao;
import com.my.db.implementations.StudentDao;
import com.my.entity.Student;

import javax.servlet.http.HttpServletRequest;

public class CreateStudent implements Command {

    private final StudentDao studentDao;
    private final GroupDao groupDao;

    public CreateStudent(StudentDao studentDao, GroupDao groupDao) {
        this.studentDao = studentDao;
        this.groupDao = groupDao;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        String[] groupsIds = request.getParameterValues("groups");

        studentDao.add(Student.builder()
                .firstName(firstName)
                .lastName(lastName)
                .groupId(Integer.parseInt(groupsIds[0]))
                .build());

        return "redirect:create_student.jsp";
    }
}
