package com.my.commands.implementations;

import com.my.commands.Command;
import com.my.db.implementations.GroupDao;
import com.my.entity.Group;
import com.my.entity.Student;

import javax.servlet.http.HttpServletRequest;

public class GetEditedGroup implements Command {

    private GroupDao groupDao;

    public GetEditedGroup(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int course = Integer.parseInt((request.getParameter("course")));

        groupDao.update(Group.builder()
                .id(id)
                .name(name)
                .course(course)
                .build());

        return "redirect:index.jsp";
    }
}
