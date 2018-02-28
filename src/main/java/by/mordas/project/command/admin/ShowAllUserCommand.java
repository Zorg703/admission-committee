package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.logic.AdminLogic;

import javax.servlet.http.HttpServlet;

public class ShowAllUserCommand implements Command {
    private AdminLogic adminLogic=new AdminLogic();
    @Override
    public void execute(HttpServlet request) {
        adminLogic.findAllUser();


    }
}
