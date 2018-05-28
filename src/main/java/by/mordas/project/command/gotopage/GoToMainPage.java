package by.mordas.project.command.gotopage;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.User;

/***
 Author: Sergei Mordas
 Date: 17.05.2018
 ***/
public class GoToMainPage implements Command {
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        User user= (User) content.getSessionAttribute(ParamConstant.USER);
        if(user!=null) {
            router.setPagePath(PageConstant.PAGE_MAIN);
        }
        else {
            router.setPagePath(PageConstant.PAGE_LOGIN);
        }
        return router;
    }
}
