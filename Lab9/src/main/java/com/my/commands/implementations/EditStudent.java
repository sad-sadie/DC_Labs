package com.my.commands.implementations;

import com.my.db.implementations.StudentDao;
import com.my.entity.Student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class EditStudent implements com.my.commands.Command {

    private StudentDao studentDao;

    public EditStudent(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public String execute(HttpServletRequest request) {

        Student student = studentDao.findById(Integer.parseInt(request.getParameter("id")));

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("student", student);


        return "edit_student.jsp";
    }
}
