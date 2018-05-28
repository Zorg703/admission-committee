package by.mordas.project.command.gotopage;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;

/***
 Author: Sergei Mordas
 Date: 15.05.2018
 ***/
public class GoToRegisterFacultyPage implements Command {
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        router.setPagePath(PageConstant.PAGE_REGISTER_ON_FACULTY);
        return router;
    }
}
