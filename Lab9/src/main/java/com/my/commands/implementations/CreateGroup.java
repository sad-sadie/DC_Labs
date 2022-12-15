package com.my.commands.implementations;

import com.my.commands.Command;
import com.my.db.implementations.GroupDao;
import com.my.entity.Group;
import com.my.entity.Student;

import javax.servlet.http.HttpServletRequest;

public class CreateGroup implements Command {

    private GroupDao groupDao;

    public CreateGroup(GroupDao groupDao) {
        this.groupDao = groupDao;
    }


    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        int course = Integer.parseInt(request.getParameter("course"));

        groupDao.add(Group.builder()
                .name(name)
                .course(course)
                .build());

        return "redirect:create_group.jsp";
    }
}
