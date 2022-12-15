package com.my.commands.implementations;

import com.my.commands.Command;
import com.my.db.implementations.GroupDao;
import com.my.entity.Group;
import com.my.entity.Student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class EditGroup implements Command {

    private GroupDao groupDao;

    public EditGroup(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public String execute(HttpServletRequest request) {

        Group group = groupDao.findById(Integer.parseInt(request.getParameter("id")));

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("group", group);


        return "edit_group.jsp";
    }
}
