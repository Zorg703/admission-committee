package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.controller.Router;
import by.mordas.project.entity.User;
import by.mordas.project.logic.AdminLogic;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowAllUserCommand implements Command {
    private AdminLogic adminLogic=new AdminLogic();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router=new Router();
        List<User> userList;
        userList=adminLogic.findAllUser();
        request.setAttribute();


    }
}
