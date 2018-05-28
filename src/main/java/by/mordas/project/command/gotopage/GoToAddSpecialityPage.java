package by.mordas.project.command.gotopage;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;

/***
 Author: Sergei Mordas
 Date: 18.04.2018
 ***/
public class GoToAddSpecialityPage implements Command {
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();

        router.setPagePath(PageConstant.PAGE_ADD_SPECIALITY);
        return router;
    }
}
