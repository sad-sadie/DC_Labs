package com.my.commands.implementations;

import com.my.commands.Command;
import com.my.db.implementations.GroupDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GetStudentForm implements Command {

    private GroupDao groupDao;
    public GetStudentForm(GroupDao groupDao) {
        this.groupDao = groupDao;
    }


    @Override
    public String execute(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("groups", groupDao.findAll());
        return "create_student.jsp";
    }
}
