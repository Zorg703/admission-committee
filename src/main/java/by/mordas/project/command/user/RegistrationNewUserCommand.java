package by.mordas.project.command.user;

import by.mordas.project.command.Command;
import by.mordas.project.controller.Router;
import by.mordas.project.logic.UserLogic;

import javax.servlet.http.HttpServletRequest;

public class RegistrationNewUserCommand implements Command {
    UserLogic userLogic=new UserLogic();
    @Override
    public Router execute(HttpServletRequest request) {

        return null;
    }

}
