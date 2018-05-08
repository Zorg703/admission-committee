package by.mordas.project.command.gotopage;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.logic.AdminLogic;
import by.mordas.project.logic.UserLogic;
import by.mordas.project.logic.impl.AdminLogicImpl;
import by.mordas.project.logic.impl.UserLogicImpl;

public class GoToAddSpecialityPage implements Command {
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();

        router.setPagePath(PageConstant.PAGE_ADD_SPECIALITY);
        return router;
    }
}
