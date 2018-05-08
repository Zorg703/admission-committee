package by.mordas.project.command.user;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.User;
import by.mordas.project.logic.AdminLogic;
import by.mordas.project.logic.LogicException;
import by.mordas.project.logic.impl.AdminLogicImpl;


public class CancelRegistrationCommand implements Command {
    private AdminLogic adminLogic=new AdminLogicImpl();

    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        User user=(User)content.getSessionAttribute(ParamConstant.USER);
        long userId=user.getUserId();
        try {
            adminLogic.canceledUserRegistration(userId);
            user.setSpecialityId(0);
            content.setSessionAttribute(ParamConstant.USER,user);
            router.setPagePath(PageConstant.PAGE_USER_SUCCESS);
        } catch (LogicException e) {
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
            router.setPagePath(PageConstant.PAGE_ERROR);
        }

        return router;
    }
}
