package by.mordas.project.command.common;

import by.mordas.project.command.Command;
import by.mordas.project.controller.Router;
import by.mordas.project.entity.User;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router=new Router();
        User user;
        router.setRouter(Router.RouteType.REDIRECT);
        return router;
    }
}
