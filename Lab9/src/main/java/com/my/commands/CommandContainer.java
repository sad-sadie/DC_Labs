package com.my.commands;


import com.my.commands.implementations.*;
import com.my.db.DaoFactory;
import com.my.db.implementations.GroupDao;
import com.my.db.implementations.StudentDao;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandContainer {


    private static final Map<String, Command> commands;

    private static final DaoFactory daoFactory = DaoFactory.getInstance();

    private static final StudentDao studentDao = daoFactory.createStudentDao();
    private static final GroupDao groupDao = daoFactory.createGroupDao();


    private CommandContainer() {
    }

    static {
        commands = new HashMap<>();
        commands.put("getStudents", new GetStudents(studentDao));
        commands.put("getGroups", new GetGroups(groupDao));
        commands.put("deleteStudent", new DeleteStudent(studentDao));
        commands.put("getStudentForm", new GetStudentForm(groupDao));
        commands.put("createStudent", new CreateStudent(studentDao, groupDao));
        commands.put("editStudent", new EditStudent(studentDao));
        commands.put("getEditedStudent", new GetEditedStudent(studentDao));
        commands.put("createGroup", new CreateGroup(groupDao));
        commands.put("deleteGroup", new DeleteGroup(groupDao));
        commands.put("editGroup", new EditGroup(groupDao));
        commands.put("getEditedGroup", new GetEditedGroup(groupDao));
        commands.put("specific", new Specific(studentDao, groupDao));
    }

    public static Command getCommand(String command) {
        return commands.get(command);
    }

    public static String doCommand(Command command, HttpServletRequest request) {
        return command.execute(request);
    }
}
