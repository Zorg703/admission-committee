package by.mordas.project.command.gotopage;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;

public class GoToDeleteSpecialityPage implements Command {
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        router.setPagePath(PageConstant.PAGE_DELETE_SPECIALITY);
        return router;
    }
}
