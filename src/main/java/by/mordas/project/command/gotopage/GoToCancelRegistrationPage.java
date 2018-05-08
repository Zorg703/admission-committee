package by.mordas.project.command.gotopage;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.User;
import by.mordas.project.logic.AdminLogic;
import by.mordas.project.logic.LogicException;
import by.mordas.project.logic.UserLogic;
import by.mordas.project.logic.impl.AdminLogicImpl;
import by.mordas.project.logic.impl.UserLogicImpl;

public class GoToCancelRegistrationPage implements Command {
    private UserLogic userLogic=new UserLogicImpl();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        User user=(User)content.getSessionAttribute(ParamConstant.USER);
        if(user.getSpecialityId()==0){
            router.setPagePath(PageConstant.PAGE_SHOW_USER_STATUS);
        }
        else {
            try {
                Speciality speciality=userLogic.findSpeciality(user.getSpecialityId());
                content.setRequestAttribute(ParamConstant.SPECIALITY,speciality);
                router.setPagePath(PageConstant.PAGE_CANCEL_REGISTRATION);
            } catch (LogicException e) {
                router.setRouter(Router.RouteType.REDIRECT);
                content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
                router.setPagePath(PageConstant.PAGE_ERROR);
            }
        }
        return router;
    }
}
