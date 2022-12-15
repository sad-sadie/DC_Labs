package com.my.commands.implementations;

import com.my.commands.Command;
import com.my.db.implementations.StudentDao;
import com.my.entity.Student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetStudents implements Command {

    private final StudentDao studentDao;

    public GetStudents(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public String execute(HttpServletRequest request) {

        List<Student> students = studentDao.findAll();

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("students", students);
        return "students.jsp";
    }
}
