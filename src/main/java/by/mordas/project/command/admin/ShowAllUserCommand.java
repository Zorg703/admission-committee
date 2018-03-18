package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.User;
import by.mordas.project.logic.AdminLogic;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowAllUserCommand implements Command {
    private AdminLogic adminLogic=new AdminLogic();

    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        List<User> userList;
        userList=adminLogic.findAllUser();
        content.setRequestAttribute(ParamConstant.USER_LIST,userList);
        router.setPagePath(PageConstant.PAGE_SHOW_USERS);
        return router;


    }
}
