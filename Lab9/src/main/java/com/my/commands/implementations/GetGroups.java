package com.my.commands.implementations;

import com.my.commands.Command;
import com.my.db.implementations.GroupDao;
import com.my.entity.Group;
import com.my.entity.Student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetGroups implements Command {

    private GroupDao groupDao;

    public GetGroups(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Group> groups = groupDao.findAll();
        System.out.println(groups);

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("groups", groups);
        return "groups.jsp";
    }
}
