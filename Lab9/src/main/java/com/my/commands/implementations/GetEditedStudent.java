package com.my.commands.implementations;

import com.my.commands.Command;
import com.my.db.implementations.StudentDao;
import com.my.entity.Student;

import javax.servlet.http.HttpServletRequest;

public class GetEditedStudent implements Command {

    private StudentDao studentDao;

    public GetEditedStudent(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        int groupId = Integer.parseInt(request.getParameter("groupId"));

        studentDao.update(Student.builder()
                        .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .groupId(groupId)
                .build());

        return "redirect:index.jsp";
    }
}
