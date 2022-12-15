package com.my.commands.implementations;

import com.my.commands.Command;
import com.my.db.implementations.GroupDao;
import com.my.db.implementations.StudentDao;
import com.my.entity.Student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class Specific implements Command {

    public StudentDao studentDao;
    private GroupDao groupDao;

    public Specific(StudentDao studentDao, GroupDao groupDao) {
        this.studentDao = studentDao;
        this.groupDao = groupDao;
    }


    @Override
    public String execute(HttpServletRequest request) {
        List<Student> students = studentDao.getStudentsByGroupName(request.getParameter("name"));
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("students", students);
        return "specific_list.jsp";
    }
}
