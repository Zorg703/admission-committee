package by.mordas.project.command.common;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;

/***
 Author: Sergei Mordas
 Date: 15.04.2018
 ***/
public class LogOutCommand implements Command {
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        content.getSession().invalidate();
        router.setPagePath(PageConstant.PAGE_LOGIN);
        router.setRouter(Router.RouteType.REDIRECT);
        return router;


    }
}
