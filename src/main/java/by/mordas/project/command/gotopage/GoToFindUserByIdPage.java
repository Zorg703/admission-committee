package by.mordas.project.command.gotopage;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;

/***
 Author: Sergei Mordas
 Date: 14.05.2018
 ***/

public class GoToFindUserByIdPage implements Command {
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        router.setPagePath(PageConstant.PAGE_FIND_USER_BY_ID);
        return router;
    }
}
