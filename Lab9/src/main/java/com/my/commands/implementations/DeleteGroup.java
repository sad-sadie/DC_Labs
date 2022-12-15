package com.my.commands.implementations;

import com.my.commands.Command;
import com.my.db.implementations.GroupDao;

import javax.servlet.http.HttpServletRequest;

public class DeleteGroup implements Command {

    private GroupDao groupDao;

    public DeleteGroup(GroupDao groupDao) {
        this.groupDao = groupDao;
    }


    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        groupDao.delete(id);
        return "index.jsp";
    }
}
