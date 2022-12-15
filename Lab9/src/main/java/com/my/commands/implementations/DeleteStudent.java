package com.my.commands.implementations;

import com.my.commands.Command;
import com.my.db.implementations.StudentDao;

import javax.servlet.http.HttpServletRequest;

public class DeleteStudent implements Command {

    private StudentDao studentDao;

    public DeleteStudent(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDao.delete(id);
        return "index.jsp";
    }
}
